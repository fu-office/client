(function(){
	var URL = {
		 CLIENT_IMPORT : "customer/import.json"	
	},Client = {
			$m : $("#client"),
			init : function(){
				this._bindEvent();
				var $list = this.$m.find(".list");
				$list.grid({
					single : true,
					height : 400,
					columns : []
				});
			},
			_bindEvent : function(){
				var $m = this.$m;
				$m.on("click", ".btn", function(){
					var $this = $(this);
					if ($this.is(".search")) {
						
					} else if ($this.is(".import")) {
						$("#client-excel").click();
					} else if ($this.is(".export")) {
						
					}
				});
				$("#client-excel").change(function(){
					var file = this.files[0];
					$.ajaxMultiForm({
						url : URL.CLIENT_IMPORT,
						data : {
							file : file
						},
						success : function(d){
							
						}
					});
					$("#client-excel").val("");
				});
			}
	},
	Gift = {
			$m : $("#gift"),
			init : function(){
				
			},
			_bindEvent : function(){
				
			}
	},
	Area = {
			$m : $("#area"),
			init : function(){
				
			},
			_bindEvent : function(){
				
			}
	};
	// init 
	(function(){
		var m = {
				gift : Gift,
				client : Client,
				area : Area
		};
		$(".tab").on("click", "li", function(){
			var name = $(this).attr("data-module");
			if (m[name].init) {
				m[name].init();
				m[name].init = false;
			} 
			$(".tab-cons").hide();
			$("#" + name).show();
			$(".tab").find("li").removeClass("tab-active");
			$(this).addClass("tab-active");
		});
		$(".tab").find("li").eq(0).click();
	})();
}());