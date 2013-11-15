
	

				<script>
				
				var typeaheadSettings = {
						
						
						    source: function(query, process) {
						        var $url =${param.url};
						        var $items = new Array;
						        var obj = this;
						        target = event.target;
						        console.log(target);
						        $items = [""];
						        $.ajax({
						            url: $url,
						            //contentType: "application/x-www-form-urlencoded; charset=UTF-16",
						            dataType: "json",
						            data: query,
						            type: "POST",
						            success: function(data) {
						                console.log(data);
						                $.map(data, function(data){
					 
						                	var group;
						                    group = {
						                        id: data.id,
						                        name: data.name,  
						                        payload: data.payload,
						                        toString: function () {
						                            return JSON.stringify(this);
						                        },
						                        toLowerCase: function () {
						                            return this.name.toLowerCase();
						                        },
						                        indexOf: function (string) {
						                            return String.prototype.indexOf.apply(this.name, arguments);
						                        },
						                        replace: function (string) {
						                            var value = '';
						                            value +=  this.name;
						                            if(typeof(this.level) != 'undefined') {
						                                value += ' <span class="pull-right muted">';
						                                value += this.level;
						                                value += '</span>';
						                            }
						                            return String.prototype.replace.apply('<div>' + value + '</div>', arguments);
						                        }
						                    };
						                    $items.push(group);
						                });

						                process($items);
						            }
						        });
						    },
						    property: 'name',
						    items: 10,
						    minLength: 2,
						    updater: function (item) {
						    	//alert(item);
						        var item = JSON.parse(item);
						        
						        console.log(item.name); 
						        
						        ${param.callback}(item,target);
						        return item.name;
						    }
						};				
				var target;
				 $(document).ready(function() {
					 
					
					 $('.typeahead').on('added',function(){
							$('.typeahead').typeahead(typeaheadSettings);
						}); 

				 });
				
				
</script>
