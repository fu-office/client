(function(){
	var URL = {
		 CLIENT_IMPORT : "customer/import.json",
		 AREA_IMPORT : "area/import.json",
		 GIFT_SAVE : "gift/save.json"
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
				this._bindEvent();
				$("#gift_add").dialog({
					autoOpen : false,
					height : "auto",
					width : 400,
					title : "登记",
					modal : true
				}).find(".btn").click(function(){
					var data = $("#gift_add").f2j();
					$.ajaxJSON({
						url : URL.GIFT_SAVE,
						data : data,
						success : function(d){
							$("#gift_add").dailog("close");
							$.msg("保存成功");
						}
					});
				});
				$("#gift_add_date").datetimepicker();
			},
			_bindEvent : function(){
				var $m = this.$m;
				$m.on("click", ".btn", function(){
					var $this = $(this);
					if ($this.is(".add")) {
						$("#gift_add").dialog("open").find("input").val("");
					} else if ($this.is(".search")) {
						
					} else if ($this.is(".import")) {
						
					} else if ($this.is(".export")) {
						
					}
				});
			}
	},
	Area = {
			$m : $("#area"),
			init : function(){
				this._bindEvent();
			},
			_bindEvent : function(){
				var $m = this.$m;
				$m.on("click", ".btn", function(){
					var $this = $(this);
					if ($this.is(".search")) {
						
					} else if ($this.is(".import")) {
						$("#area-excel").click();
					} else if ($this.is(".export")) {
						
					}
				});
				$("#area-excel").change(function(){
					var file = this.files[0];
					$.ajaxMultiForm({
						url : URL.AREA_IMPORT,
						data : {
							file : file
						},
						success : function(d){
							
						}
					});
					$("#area-excel").val("");
				});
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