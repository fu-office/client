(function(){
	$.fn.j2f = function(obj){
		obj = obj || {};
		var $this = $(this);
		$this.find("input[name]").each(function(){
			this.value = obj[this.name] || "";
		});
		$this.find("select[name]").each(function(){
			$(this).select("value", obj[this.name]);
		});
	};
	var URL = {
		 CLIENT_IMPORT : "customer/import.json",
		 AREA_IMPORT : "area/import.json",
		 AREA_EXPORT : "area/export.json",
		 AREA_SEARCH : "area/search.json",
		 AREA_DELETE : "area/delete.json",
		 AREA_SAVE_OR_UPDATE : "area/saveOrUpdate.json",
		 AREA_PARAMS : "area/findAll.json",
		 GIFT_SAVE : "gift/save.json",
		 GIFT_IMPORT : "gift/import.json",
		 GIFT_EXPORT : "gift/export.json",
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
							$.msg("导入成功");
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
							$("#gift_add").dialog("close");
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
						$("#gift_excel").click();
					} else if ($this.is(".export")) {
						
					}
				});
				$("#gift_excel").change(function(){
					var file = this.files[0];
					$.ajaxMultiForm({
						url : URL.GIFT_IMPORT,
						data : {
							file : file
						},
						success : function(){
							$.msg("导入成功");
						}
					});
				});
			}
	},
	Area = {
			$m : $("#area"),
			init : function(){
				var $m = this.$m,
					_self = this;
				this._bindEvent();
				$m.find(".list").grid({
					single : true,
					height : 400, 
					columns : [
					       {title : "序号", field : "index", width : 50,formatter : function(ui, data){
					    	   return data.index + 1;
					       }},
					       {title : "省", field : "prov"},
				           {title : "市", field : "city"},
				           {title : "专柜状态", field : "shopState"},
				           {title : "操作", field : "opera", formatter : function(ui, data){
				        	   return "<a href='javascript:void(0);' data-index='" + data.index + 
				        	   	"' class='edit-link'>修改</a> &nbsp;&nbsp;<a href='javascript:void(0);' " +
				        	   	"class='delete-link' data-index='" + data.index + "'>删除</a>";
				           }}] 
				});
				$("#area_update_dialog").dialog({
					height : "auto",
					autoOpen : false,
					modal : true,
					width : 400,
					title : "更新"
				}).find(".submit-update").click(function(){
					_self.saveOrUpdate($("#area_update_dialog").f2j(), function(d){
						$("#area_update_dialog").dialog("close");
					});
				});
			},
			saveOrUpdate : function(area, fn){
				if (area.prov && area.city) {
					$.ajaxJSON({
						url : URL.AREA_SAVE_OR_UPDATE,
						data : area,
						success : function(d){
							$.msg("保存成功");
							fn && fn(d);
						}
					});
				} else {
					$.msg("请输入省，市");
				} 
			},
			search : function(data){
				var _self = this;
				$.ajaxJSON({
					url : URL.AREA_SEARCH,
					data : data,
					success : function(d){
						_self.$m.find(".list").grid("loadData", {
							rows : d.list
						});
					}
				});
			},
			exportExcel : function(data){
				$.dlFile({
					url : URL.AREA_EXPORT,
					data : data,
					fileName : "区域名单.xls"
				});
			},
			_bindEvent : function(){
				var $m = this.$m,
					_self = this;
				$m.on("click", ".btn", function(){
					var $this = $(this);
					if ($this.is(".search")) {
						_self.search($m.find(".fm-row").f2j());
					} else if ($this.is(".import")) {
						$("#area-excel").click();
					} else if ($this.is(".export")) {
						_self.exportExcel($m.find(".fm-row").f2j());
					} else if ($this.is(".add")) {
						_self.editArea({});
					}
				});
				$m.find(".list").on("click", "a", function(){
					var $link = $(this),
						index = $link.attr("data-index"),
						row = $m.find(".list").grid("getRows")[index]; 
					if ($link.is(".edit-link")) {
						_self.editArea(row);
					} else if ($link.is(".delete-link")) {
						$.msg({
							type : "confirm",
							msg : "确定删除？",
							ok : function(){
								_self.deleteArea(row.id, function(){
									$m.find(".list").grid("deleteRow", index);
									$m.find(".list").grid("reload");
								});
							}
						});
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
							$.msg("导入成功");
						}
					});
					$("#area-excel").val("");
				});
			},
			editArea : function(area){
				if (area.id) {
					$("#area_update_dialog").dialog("option", {
						title : "更新"
					}).find("input").prop("readonly", true);
				} else {
					$("#area_update_dialog").dialog("option", {
						title : "新建"
					}).find("input").prop("readonly", false);
				}
				$("#area_update_dialog").dialog("open").j2f(area);
			},
			deleteArea : function(id, fn){
				$.ajaxJSON({
					url : URL.AREA_DELETE,
					data : {
						id : id
					},
					success : function(d){
						$.msg("删除成功");
						fn && fn(d);
					}
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