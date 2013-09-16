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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

@Service
public class FileFormService {
	@Autowired
	private GridFsTemplate gridFsTemplate;

	public List<FileMeta> upload(List<FileMeta> files,
			MultipartHttpServletRequest request, HttpServletResponse response) {

		// 1. build an iterator
		Iterator<String> it = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (it.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(it.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! "
					+ files.size());

			// 2.3 create new fileMeta
			FileMeta fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());

			try {

				DBObject metaData = new BasicDBObject();

				metaData.put("contentType", mpf.getContentType());
				GridFSFile gridfsfile = gridFsTemplate.store(
						mpf.getInputStream(), mpf.getOriginalFilename(),
						metaData);
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

	public FileMeta upload(MultipartHttpServletRequest request,
			HttpServletResponse response) {

		// 1. build an iterator
		Iterator<String> it = request.getFileNames();
		MultipartFile mpf = null;
		System.out.println("gridfs-upload");
		// 2. get each file
		if (it.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(it.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! ");

			// 2.3 create new fileMeta
			FileMeta fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			try {
				DBObject metaData = new BasicDBObject();

				metaData.put("contentType", mpf.getContentType());
				GridFSFile gridfsfile = gridFsTemplate.store(
						mpf.getInputStream(), mpf.getOriginalFilename(),
						metaData);
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

	public List<FileMeta> remove(List<FileMeta> files,
			HttpServletResponse response, String value) {
		files.remove(Integer.parseInt(value));
		return files;
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

	public void get(String id, HttpServletResponse response) throws IOException{
		System.out.println("get-id"+id);
		
		GridFSDBFile gridfsdbfile = gridFsTemplate.findOne(query(where("_id").is(new ObjectId(id))));
		response.setContentType(gridfsdbfile.getContentType());
		response.setHeader("Content-disposition", "attachment; filename=\""
				+ gridfsdbfile.getFilename() + "\"");
		gridfsdbfile.writeTo(response.getOutputStream());
	}
}
