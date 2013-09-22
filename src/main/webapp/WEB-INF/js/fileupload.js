$(function() {
	$('#fileupload').fileupload(
					{
						dataType : 'json',


						progressall : function(e, data) {
							var progress = parseInt(data.loaded / data.total
									* 100, 10);
							$('#progress .bar').css('width', progress + '%');
						},

						dropZone : $('#dropzone'),
						done :   function(e, data) {
							
						
							$("#imgcontainer").html(data.result.url);
							$("#imgcontainer").html("<img src='" + data.result.url + "' style='width:100%;'/>");
						}		
					}
			);


});

