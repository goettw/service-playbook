package serviceplaybook.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import serviceplaybook.model.FileMeta;
import serviceplaybook.model.MongoLocalEntity;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

@Service
public class FileFormService {
	@Autowired
	private GridFsTemplate gridFsTemplate;

	/**
	 * @TODO rework things
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 */
	public List<FileMeta> upload(List<FileMeta> files, MultipartHttpServletRequest request, HttpServletResponse response) {

		Iterator<String> it = request.getFileNames();
		MultipartFile mpf = null;

		while (it.hasNext()) {

			mpf = request.getFile(it.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());

			// 2.3 create new fileMeta
			FileMeta fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());

			try {

				DBObject metaData = new BasicDBObject();

				metaData.put("contentType", mpf.getContentType());
				GridFSFile gridfsfile = gridFsTemplate.store(mpf.getInputStream(), mpf.getOriginalFilename(), metaData);
				System.out.println("gridfs-id" + gridfsfile.getId());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 2.4 add to files
			files.add(fileMeta);
		}
		return files;
	}

	/**
	 * Stores one file in the GridFS store.
	 * 
	 * @param mongoLocalEntity
	 *            the local mongo entity where the file belongs to
	 * @param request
	 * @param response
	 * @param unique
	 *            if true, the function deletes entity with same id before
	 *            storing the new one
	 * @return the metadata of the file
	 */
	public FileMeta upload(MongoLocalEntity mongoLocalEntity, MultipartHttpServletRequest request, HttpServletResponse response, boolean unique) {

		// 1. build an iterator
		Iterator<String> it = request.getFileNames();
		MultipartFile mpf = null;

		System.out.println("gridfs-upload");
		// 2. get each file

		if (it.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(it.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! ");
			if (unique) {
				List<GridFSDBFile> files = findFiles(mongoLocalEntity);
				for (Iterator<GridFSDBFile> it2 = files.iterator(); it2.hasNext();) {
					GridFSDBFile file = it2.next();
					gridFsTemplate.delete(query(where("_id").is(file.getId())));
					System.out.println("deleted " + file.getId());
				}
			}
			// 2.3 create new fileMeta
			FileMeta fileMeta = new FileMeta();
			fileMeta.setSource(mongoLocalEntity);
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			// fileMeta.setId(id);
			try {
				DBObject metaData = new BasicDBObject();

				metaData.put("contentType", mpf.getContentType());
				metaData.put("sourceCollection", fileMeta.getSource().getCollectionName());
				metaData.put("sourceId", fileMeta.getSource().getId());
				if (mongoLocalEntity.getFolder() != null) {
					metaData.put("sourceFolder", mongoLocalEntity.getFolder());
				}
				GridFSFile gridfsfile = gridFsTemplate.store(mpf.getInputStream(), mpf.getOriginalFilename(), metaData);
				fileMeta.setId(gridfsfile.getId().toString());
				System.out.println("gridfs-id" + gridfsfile.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 2.4 add to files

			return fileMeta;
		}
		return null;
	}

	public List<FileMeta> remove(List<FileMeta> files, HttpServletResponse response, String value) {
		files.remove(Integer.parseInt(value));
		return files;
	}
	
	public List<GridFSDBFile> findFiles(MongoLocalEntity mongoLocalEntity) {
		return gridFsTemplate.find(query(where("metadata.sourceCollection").is(mongoLocalEntity.getCollectionName())
				.and("metadata.sourceId").is(mongoLocalEntity.getId()).and("metadata.sourceFolder").is(mongoLocalEntity.getFolder()))); 

	}

	/***************************************************
	 * URL: /rest/controller/get/{value} get(): get file as an attachment
	 * 
	 * @param response
	 *            : passed by the server
	 * @param value
	 *            : value from the URL
	 * @return void
	 ****************************************************/

	public void get(String id, HttpServletResponse response) throws IOException {
		System.out.println("get-id" + id);

		GridFSDBFile gridfsdbfile = gridFsTemplate.findOne(query(where("_id").is(new ObjectId(id))));
		response.setContentType(gridfsdbfile.getContentType());
		response.setHeader("Content-disposition", "attachment; filename=\"" + gridfsdbfile.getFilename() + "\"");
		gridfsdbfile.writeTo(response.getOutputStream());
	}
}
