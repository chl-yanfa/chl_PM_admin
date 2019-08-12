/**
 * 每条数据带标题，每行中间有间隙的样式
 **/
var tcwOrderInfoView = $.extend({}, $.fn.datagrid.defaults.view, {
	render : function(target, container, frozen) {
		var datagrid = $.data(target, "datagrid");
		var opts = datagrid.options;
		var rows = datagrid.data.rows;
		var fields = $(target).datagrid("getColumnFields", frozen);
		if (frozen) {
			if (!(opts.rownumbers || (opts.frozenColumns && opts.frozenColumns.length))) {
				return;
			}
		}
		var table = [ "<table class=\"datagrid-btable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>" ];
		for ( var i = 0; i < rows.length; i++) {
			var css = opts.rowStyler ? opts.rowStyler.call(target, i, rows[i]) : "";
			var classValue = "";
			var styleValue = "";
			if (typeof css == "string") {
				styleValue = css;
			} else {
				if (css) {
					classValue = css["class"] || "";
					styleValue = css["style"] || "";
				}
			}
			var cls = "class=\"datagrid-row " + classValue + "\"";
			var style = styleValue ? "style=\"" + styleValue + "\"" : "";
			var rowId = datagrid.rowIdPrefix + "-" + (frozen ? 1 : 2) + "-" + i;
			table.push("<tr id=\"split"+i+"\"><td style='height:7px;border:none' colspan=\""+fields.length+"\"></td></tr>");
			if(opts.rowTitleFormatter){
				var rownumber = i + 1;
				if (opts.pagination) {
					rownumber += (opts.pageNumber - 1) * opts.pageSize;
				}
				var rowTitleHtml = opts.rowTitleFormatter(rows[i]);
				rowTitleHtml = "<span class='rownumber'>"+rownumber+"</span>"+rowTitleHtml;
				table.push("<tr class='rowTitleStyle'><td colspan=\""+fields.length+"\">"+rowTitleHtml+"</td></tr>");
			}
			table.push("<tr id=\"" + rowId + "\" datagrid-row-index=\"" + i + "\" " + cls + " " + style + ">");
			table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));
			table.push("</tr>");
		}
		table.push("</tbody></table>");
		$(container).html(table.join(""));
	},
	onBeforeRender:function(target,rows){
		var datagrid = $.data(target, "datagrid");
		datagrid.panel.find("div.datagrid-view1").remove();
	},
	onAfterRender:function(target,rows){
		//给TR标头添加点击事件
		var datagrid = $.data(target, "datagrid");
		datagrid.panel.find("tr.rowTitleStyle").click(function(){
			$(this).next("tr.datagrid-row").click();
		});
		var opts = datagrid.options;
		if(!opts.fit){
			datagrid.panel.find("div.datagrid-view2").find("table.datagrid-htable,table.datagrid-btable").css("width","100%");
		}
	},
	renderRow : function(target, fields, frozen, rowIndex, rowData) {
		var opts = $.data(target, "datagrid").options;
		var cc = [];
		if (frozen && opts.rownumbers) {
			var rownumber = rowIndex + 1;
			if (opts.pagination) {
				rownumber += (opts.pageNumber - 1) * opts.pageSize;
			}
			//cc.push("<td class=\"datagrid-td-rownumber\"><div class=\"datagrid-cell-rownumber\">" + rownumber + "</div></td>");
		}
		for ( var i = 0; i < fields.length; i++) {
			var field = fields[i];
			var col = $(target).datagrid("getColumnOption", field);
			if (col) {
				var row = rowData[field];
				var css = col.styler ? (col.styler(row, rowData, rowIndex) || "") : "";
				var classValue = "";
				var styleValue = "";
				if (typeof css == "string") {
					styleValue = css;
				} else {
					if (css) {
						classValue = css["class"] || "";
						styleValue = css["style"] || "";
					}
				}
				if(i==0){
					styleValue+=";border-left:solid 1px #E5E5E5";
				}
				var cls = classValue ? "class=\"" + classValue + "\"" : "";
				var style = col.hidden ? "style=\"display:none;" + styleValue + "\"" : (styleValue ? "style=\"" + styleValue + "\"" : "");
				cc.push("<td field=\"" + field + "\" " + cls + " " + style + ">");
				var style = "";
				if (!col.checkbox) {
					if (col.align) {
						style += "text-align:" + col.align + ";";
					}
					if (!opts.nowrap) {
						style += "white-space:normal;height:auto;word-break:break-all";
					} else {
						if (opts.autoRowHeight) {
							style += "height:auto;";
						}
					}
				}
				cc.push("<div style=\"" + style + "\" ");
				cc.push(col.checkbox ? "class=\"datagrid-cell-check\"" : "class=\"datagrid-cell " + col.cellClass + "\"");
				cc.push(">");
				if (col.checkbox) {
					cc.push("<input type=\"checkbox\" " + (rowData.checked ? "checked=\"checked\"" : ""));
					cc.push(" name=\"" + field + "\" value=\"" + (row != undefined ? row : "") + "\">");
				} else {
					if (col.formatter) {
						cc.push(col.formatter(row, rowData, rowIndex));
					} else {
						cc.push(row);
					}
				}
				cc.push("</div>");
				cc.push("</td>");
			}
		}
		return cc.join("");
	} 
});

/**
 * 每行中间有间隙的样式
 **/
var tcwSplitView = $.extend({}, $.fn.datagrid.defaults.view, {
	render : function(target, container, frozen) {
		var datagrid = $.data(target, "datagrid");
		var opts = datagrid.options;
		var rows = datagrid.data.rows;
		var fields = $(target).datagrid("getColumnFields", frozen);
		if (frozen) {
			if (!(opts.rownumbers || (opts.frozenColumns && opts.frozenColumns.length))) {
				return;
			}
		}
		var table = [ "<table class=\"datagrid-btable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>" ];
		for ( var i = 0; i < rows.length; i++) {
			var css = opts.rowStyler ? opts.rowStyler.call(target, i, rows[i]) : "";
			var classValue = "";
			var styleValue = "";
			if (typeof css == "string") {
				styleValue = css;
			} else {
				if (css) {
					classValue = css["class"] || "";
					styleValue = css["style"] || "";
				}
			}
			var cls = "class=\"datagrid-row " + classValue + "\"";
			var style = styleValue ? "style=\"" + styleValue + "\"" : "";
			var rowId = datagrid.rowIdPrefix + "-" + (frozen ? 1 : 2) + "-" + i;
			table.push("<tr><td style='height:7px;border:none;padding:0' colspan=\""+fields.length+"\"></td></tr>");
			table.push("<tr id=\"" + rowId + "\" datagrid-row-index=\"" + i + "\" " + cls + " " + style + ">");
			table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));
			table.push("</tr>");
		}
		table.push("</tbody></table>");
		$(container).html(table.join(""));
	},
	onBeforeRender:function(target,rows){
		var datagrid = $.data(target, "datagrid");
		var opts = $.data(target, "datagrid").options;
		var datagrid2 = datagrid.panel.find("div.datagrid-view2");
		datagrid2.find("div.datagrid-header").css("border-left","none");
		datagrid2.find("div.datagrid-header").find("input[type='checkbox']").remove();
		datagrid.panel.find("div.datagrid-view1 div.datagrid-header").css("border-right","none");
	},
	onAfterRender:function(target,rows){
		var datagrid = $.data(target, "datagrid");
		var opts = datagrid.options;
		if(!opts.fit){
			datagrid.panel.find("div.datagrid-view2").find("table.datagrid-htable,table.datagrid-btable").css("width","100%");
		}
	},
	renderRow : function(target, fields, frozen, rowIndex, rowData) {
		var opts = $.data(target, "datagrid").options;
		var cc = [];
		if (frozen && opts.rownumbers) {
			var rownumber = rowIndex + 1;
			if (opts.pagination) {
				rownumber += (opts.pageNumber - 1) * opts.pageSize;
			}
			
			if(opts.numberFormatter){
				cc.push('<td class=\"datagrid-td-rownumber split-view-rownumber\">'+opts.numberFormatter(rowData,rowIndex,rownumber)+'</td>');
			}else{
				cc.push("<td class=\"datagrid-td-rownumber split-view-rownumber\"><div class=\"datagrid-cell-rownumber\">" + rownumber + "</div></td>");
			}
		}
		for ( var i = 0; i < fields.length; i++) {
			var field = fields[i];
			var col = $(target).datagrid("getColumnOption", field);
			if (col) {
				var row = rowData[field];
				var css = col.styler ? (col.styler(row, rowData, rowIndex) || "") : "";
				var classValue = "";
				var styleValue = "";
				if (typeof css == "string") {
					styleValue = css;
				} else {
					if (css) {
						classValue = css["class"] || "";
						styleValue = css["style"] || "";
					}
				}
				styleValue+=";border-top:solid 1px #E5E5E5";
				var cls = classValue ? "class=\"" + classValue + "\"" : "";
				var style = col.hidden ? "style=\"display:none;" + styleValue + "\"" : (styleValue ? "style=\"" + styleValue + "\"" : "");
				cc.push("<td field=\"" + field + "\" " + cls + " " + style + ">");
				var style = "";
				if (!col.checkbox) {
					if (col.align) {
						style += "text-align:" + col.align + ";";
					}
					if (!opts.nowrap) {
						style += "white-space:normal;height:auto;word-break:break-all";
					} else {
						if (opts.autoRowHeight) {
							style += "height:auto;";
						}
					}
				}
				
				cc.push("<div style=\"" + style + "\" ");
				cc.push(col.checkbox ? "class=\"datagrid-cell-check\"" : "class=\"datagrid-cell " + col.cellClass + "\"");
				cc.push(">");
				if (col.checkbox) {
					cc.push("<input type=\"checkbox\" " + (rowData.checked ? "checked=\"checked\"" : ""));
					cc.push(" name=\"" + field + "\" value=\"" + (row != undefined ? row : "") + "\">");
				} else {
					if (col.formatter) {
						cc.push(col.formatter(row, rowData, rowIndex));
					} else {
						cc.push(row);
					}
				}
				cc.push("</div>");
				cc.push("</td>");
			}
		}
		return cc.join("");
	} 
});

/**
 * 简单列表样式
 **/
var tcwSimpleListView = $.extend({}, $.fn.datagrid.defaults.view, {
	render : function(target, container, frozen) {
		var datagrid = $.data(target, "datagrid");
		var opts = datagrid.options;
		var rows = datagrid.data.rows;
		var fields = $(target).datagrid("getColumnFields", frozen);
		if (frozen) {
			if (!(opts.rownumbers || (opts.frozenColumns && opts.frozenColumns.length))) {
				return;
			}
		}
		var table = [ "<table class=\"datagrid-btable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>" ];
		for ( var i = 0; i < rows.length; i++) {
			var css = opts.rowStyler ? opts.rowStyler.call(target, i, rows[i]) : "";
			var classValue = "";
			var styleValue = "";
			if (typeof css == "string") {
				styleValue = css;
			} else {
				if (css) {
					classValue = css["class"] || "";
					styleValue = css["style"] || "";
				}
			}
			var cls="class=\"datagrid-row "+(i%2&&opts.striped?"datagrid-row-alt ":" ")+classValue+"\"";
			var style = styleValue ? "style=\"" + styleValue + "\"" : "";
			var rowId = datagrid.rowIdPrefix + "-" + (frozen ? 1 : 2) + "-" + i;
			if(i==0){
				table.push("<tr><td style='height:7px;border:none;padding:0;border-bottom:solid 1px #E5E5E5' colspan=\""+fields.length+"\"></td></tr>");
			}
			table.push("<tr id=\"" + rowId + "\" datagrid-row-index=\"" + i + "\" " + cls + " " + style + ">");
			table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));
			table.push("</tr>");
		}
		table.push("</tbody></table>");
		$(container).html(table.join(""));
	},
	onBeforeRender:function(target,rows){
		var datagrid = $.data(target, "datagrid");
		//var opts = datagrid.options;
		var datagrid2 = datagrid.panel.find("div.datagrid-view2");
		datagrid2.find("div.datagrid-header").css("border-left","none");
		//datagrid2.find("div.datagrid-header").find("input[type='checkbox']").remove();
		//datagrid.panel.find("table.datagrid-btable").css("width","100%");
		//datagrid.panel.find("div.datagrid-header").addClass("simpleListHeader");
	},
	onAfterRender:function(target,rows){
		var datagrid = $.data(target, "datagrid");
		var opts = datagrid.options;

		if(!opts.fit){
			datagrid.panel.find("div.datagrid-view2").find("table.datagrid-htable,table.datagrid-btable").css("width","100%");
		}
		//网易订单特殊需求，勿动
		if(datagrid.data && datagrid.data.flag){
			$("#payAmount").html(datagrid.data.flag);
		}
		if(datagrid.data && datagrid.data.mark){
			$("#servicePriceAmount").html(datagrid.data.mark);
		}
	},
	renderRow : function(target, fields, frozen, rowIndex, rowData) {
		var opts = $.data(target, "datagrid").options;
		var cc = [];
		if (frozen && opts.rownumbers) {
			var rownumber = rowIndex + 1;
			if (opts.pagination) {
				rownumber += (opts.pageNumber - 1) * opts.pageSize;
			}
			var numberClass = "";
			if(opts.striped){
				numberClass = rownumber%2==0?"datagrid-row-alt":"datagrid-row-normal";
			}
			var rownumbershow = rownumber.toString();
			if(rownumber>999){
				rownumbershow = '.'+rownumbershow.substring(rownumbershow.length-2,rownumbershow.length);
			}
			if(opts.numberFormatter){
				cc.push('<td class=\"datagrid-td-rownumber split-view-rownumber\">'+opts.numberFormatter(rowData,rowIndex,rownumber)+'</td>');
			} else {
				cc.push("<td class=\"datagrid-td-rownumber "+numberClass+"\" style='border-left:solid 1px #E5E5E5;border-right:none'><div class=\"datagrid-cell-rownumber\" title=\""+rownumber+"\">" + rownumbershow + "</div></td>");
			}
		}
		for ( var i = 0,j = fields.length;i<j; i++) {
			var field = fields[i];
			var col = $(target).datagrid("getColumnOption", field);
			if (col) {
				var row = rowData[field];
				var css = col.styler ? (col.styler(row, rowData, rowIndex) || "") : "";
				var classValue = "";
				var styleValue = "";
				if (typeof css == "string") {
					styleValue = css;
				} else {
					if (css) {
						classValue = css["class"] || "";
						styleValue = css["style"] || "";
					}
				}
				var cls = classValue ? "class=\"" + classValue + "\"" : "";
				var style = col.hidden ? "style=\"display:none;" + styleValue + "\"" : (styleValue ? "style=\"" + styleValue + "\"" : "");
				cc.push("<td field=\"" + field + "\" " + cls + " " + style + ">");
				var style = "";
				if (!col.checkbox) {
					if (col.align) {
						style += "text-align:" + col.align + ";";
					}
					if (!opts.nowrap) {
						style += "white-space:normal;height:auto;word-break:break-all";
					} else {
						if (opts.autoRowHeight) {
							style += "height:auto;";
						}
					}
				}
				cc.push("<div style=\"" + style + "\" ");
				cc.push(col.checkbox ? "class=\"datagrid-cell-check\"" : "class=\"datagrid-cell " + col.cellClass + "\"");
				cc.push(">");
				if (col.checkbox) {
					cc.push("<input type=\"checkbox\" " + (rowData.checked ? "checked=\"checked\"" : ""));
					cc.push(" name=\"" + field + "\" value=\"" + (row != undefined ? row : "") + "\">");
				} else {
					if (col.formatter) {
						cc.push(col.formatter(row, rowData, rowIndex));
					} else {
						cc.push(row);
					}
				}
				cc.push("</div>");
				cc.push("</td>");
			}
		}
		return cc.join("");
	} 
});

/**
 * 弹出框中的简单列表样式
 **/
var tcwSimpleListViewForDialog = $.extend({}, $.fn.datagrid.defaults.view, {
	render : function(target, container, frozen) {
		var datagrid = $.data(target, "datagrid");
		var opts = datagrid.options;
		var rows = datagrid.data.rows;
		var fields = $(target).datagrid("getColumnFields", frozen);
		if (frozen) {
			if (!(opts.rownumbers || (opts.frozenColumns && opts.frozenColumns.length))) {
				return;
			}
		}
		var table = [ "<table class=\"datagrid-btable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>" ];
		for ( var i = 0; i < rows.length; i++) {
			var css = opts.rowStyler ? opts.rowStyler.call(target, i, rows[i]) : "";
			var classValue = "";
			var styleValue = "";
			if (typeof css == "string") {
				styleValue = css;
			} else {
				if (css) {
					classValue = css["class"] || "";
					styleValue = css["style"] || "";
				}
			}
			var cls="class=\"datagrid-row "+(i%2&&opts.striped?"datagrid-row-alt ":" ")+classValue+"\"";
			var style = styleValue ? "style=\"" + styleValue + "\"" : "";
			var rowId = datagrid.rowIdPrefix + "-" + (frozen ? 1 : 2) + "-" + i;
			if(i==0){
				table.push("<tr><td style='height:7px;border:none;padding:0;border-bottom:solid 1px #E5E5E5' colspan=\""+fields.length+"\"></td></tr>");
			}
			table.push("<tr id=\"" + rowId + "\" datagrid-row-index=\"" + i + "\" " + cls + " " + style + ">");
			table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));
			table.push("</tr>");
		}
		table.push("</tbody></table>");
		$(container).html(table.join(""));
	},
	onBeforeRender:function(target,rows){
		var datagrid = $.data(target, "datagrid");
		var opts = datagrid.options;
		var datagrid2 = datagrid.panel.find("div.datagrid-view2");
		datagrid2.find("div.datagrid-header").css("border-left","none");
		datagrid2.find("div.datagrid-header").find("input[type='checkbox']").remove();
		datagrid.panel.find("div.datagrid-view1 div.datagrid-header").css("border-right","none");
		//datagrid.panel.find("div.datagrid-header").addClass("simpleListHeader");
		datagrid.panel.find("div.datagrid-header").css("background-color","#C6E4FF")
					  .find("td").css("border-right","solid 1px #C6E4FF");
	},
	onAfterRender:function(target,rows){
		var datagrid = $.data(target, "datagrid");
		var opts = datagrid.options;
		if(!opts.fit){
			datagrid.panel.find("div.datagrid-view2").find("table.datagrid-htable,table.datagrid-btable").css("width","100%");
		}
	},
	renderRow : function(target, fields, frozen, rowIndex, rowData) {
		var opts = $.data(target, "datagrid").options;
		var cc = [];
		if (frozen && opts.rownumbers) {
			var rownumber = rowIndex + 1;
			if (opts.pagination) {
				rownumber += (opts.pageNumber - 1) * opts.pageSize;
			}
			var numberClass = "";
			if(opts.striped){
				numberClass = rownumber%2==0?"datagrid-row-alt":"datagrid-row-normal";
			}
			var rownumbershow = rownumber.toString();
			if(rownumber>999){
				rownumbershow = '.'+rownumbershow.substring(rownumbershow.length-2,rownumbershow.length);
			}
			cc.push("<td class=\"datagrid-td-rownumber "+numberClass+"\" style='border-left:solid 1px #E5E5E5;border-right:none'><div class=\"datagrid-cell-rownumber\" title=\""+rownumber+"\">" + rownumbershow + "</div></td>");
		}
		for ( var i = 0,j = fields.length;i<j; i++) {
			var field = fields[i];
			var col = $(target).datagrid("getColumnOption", field);
			if (col) {
				var row = rowData[field];
				var css = col.styler ? (col.styler(row, rowData, rowIndex) || "") : "";
				var classValue = "";
				var styleValue = "";
				if (typeof css == "string") {
					styleValue = css;
				} else {
					if (css) {
						classValue = css["class"] || "";
						styleValue = css["style"] || "";
					}
				}
				var cls = classValue ? "class=\"" + classValue + "\"" : "";
				var style = col.hidden ? "style=\"display:none;" + styleValue + "\"" : (styleValue ? "style=\"" + styleValue + "\"" : "");
				cc.push("<td field=\"" + field + "\" " + cls + " " + style + ">");
				var style = "";
				if (!col.checkbox) {
					if (col.align) {
						style += "text-align:" + col.align + ";";
					}
					if (!opts.nowrap) {
						style += "white-space:normal;height:auto;word-break:break-all";
					} else {
						if (opts.autoRowHeight) {
							style += "height:auto;";
						}
					}
				}
				cc.push("<div style=\"" + style + "\" ");
				cc.push(col.checkbox ? "class=\"datagrid-cell-check\"" : "class=\"datagrid-cell " + col.cellClass + "\"");
				cc.push(">");
				if (col.checkbox) {
					cc.push("<input type=\"checkbox\" " + (rowData.checked ? "checked=\"checked\"" : ""));
					cc.push(" name=\"" + field + "\" value=\"" + (row != undefined ? row : "") + "\">");
				} else {
					if (col.formatter) {
						cc.push(col.formatter(row, rowData, rowIndex));
					} else {
						cc.push(row);
					}
				}
				cc.push("</div>");
				cc.push("</td>");
			}
		}
		return cc.join("");
	} 
});