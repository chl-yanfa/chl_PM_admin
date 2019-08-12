$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	reLoadData(0);
});
//初始化数据表格
function initDateGrid(){
	$("#procedureList").datagrid({
    	loadMsg : "数据加载中,请稍后……",
        nowrap : false,/*换行*/
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getProcedureList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        rowTitleFormatter:function(row){
        	return "拍品编码："+row['auctionNo'];
        },
        columns : [[
            {title : '拍品基本信息', field : 'carCode', width : 260, align : 'left',halign:'center',formatter: function(value,rec,index){
            	return carHeadInfo(rec);
            }},
            {title : '在库手续', field : 'procedureFileList', width : 200, align : 'left',halign:'center',formatter:function(value,row,index){
            	if(value!=null&&value.length>0){
            		var str='<div class="inProcedure">';
            		for(var key in fileNameList){
            			var res=getProcedureName(key,value);
            			if(res==0){
            				str+='<div class="proce-div"><img src="../../resources/images/has.png" width="15px" height="15px"/>'
            					+'<label>'+fileNameList[key]+'</label></div>';
            			}else if(res==1){
            				str+='<div class="proce-div"><span class="redCircle"><span class="sline">‐</span></span>'
            					+'<label>'+fileNameList[key]+'</label></div>';
            			}else if(res==-1){
            				str+='<div class="proce-div"><img src="../../resources/images/no.png" width="15px" height="15px"/>'
            					+'<label>'+fileNameList[key]+'</label></div>';
            			}
            		}
            		str+='</div>';
            		return str;
            	}else{
            		var str='<div class="inProcedure">';
            		for(var i in fileNameList){
            			str+='<div class="proce-div"><img src="../../resources/images/no.png" width="15px" height="15px"/>'
        					+'<label>'+fileNameList[i]+'</label></div>';
            		}
            		str+='</div>';
            		return str;
            	}
            }},
            {title : '最新入库时间',field:'newestInTime',width:50,align:'center'},
            {title : '最后出库时间',field:'latestOutTime',width:50,align:'center'},
            {title : '是否归档',field:'isRecord',width:40,align:'center'},
            {title : '拍品状态',field:'vehicleState',width:50,align:'center'},
            {title : '操作', field : 'id', width : 50, align : 'center',formatter:function(value,row,index){
            	var auctionId = row.auctionId;
            	var procedureId = row.procedureId;
            	var optionStr = '';
            	var addProcedure="addProcedure('"+auctionId+"','"+procedureId+"')";
            	var openOutofProcedure="openOutofProcedure('"+procedureId+"')";
            	var outProcedureAudit="outProcedureAudit('"+auctionId+"','"+procedureId+"')";
            	var getProcedureRecord="getProcedureRecord('"+auctionId+"','"+procedureId+"')";
            	optionStr += '<a href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;" onclick="'+addProcedure+'">入库</a><br>';
            	optionStr += '<a href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;" onclick="'+openOutofProcedure+'">出库</a><br>';
            	optionStr += '<a href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;" onclick="'+outProcedureAudit+'">出库审核</a><br>';
            	optionStr += '<a href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;" onclick="'+getProcedureRecord+'">查看归档</a>';
            	return optionStr;
           }}
        ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true,
        fitStyle : true
    });
	//拍品类型
	$('#tb-auctionType').combobox({
    	editable : false,
    	width : 150,
		valueField:'id',
		textField:'value',
		data: [{id:"0",value: "全车配件"},{id:"1",value: "高价值配件"},{id:"2",value: "大宗配件"}],
		panelHeight:"auto"
	});
	//审核状态
	$('#tb-subCompanyAudit').combobox({
    	editable : false,
    	width : 150,
		valueField : 'id',
		textField : 'value',
		data: [{id:"0",value: "待审核"},{id:"1",value: "审核通过"},{id:"2",value: "已驳回"}],
		panelHeight:"auto"
	});
	//是否归档
	$('#tb-isRecord').combobox({
		editable : false,
		width : 150,
		valueField:'id',
		textField:'value',
		data: [{id:"0",value: "否"},{id:"1",value: "是"}],
		panelHeight:"auto"
	});
	//入库时间
	$('#tb-inTimeStart').datetimebox({
		editable : false,
		width : 180,
		showSeconds : false
	});
	$('#tb-inTimeEnd').datetimebox({
		editable : false,
		width : 180,
		showSeconds : false
	});
	$('#searchText').searchbox({
		   width :222,
		   prompt : '品牌型号/车牌号',
		   searcher:function(value,name){
			   $('#searchText').searchbox("setValue",value.toUpperCase());
			   reLoadData(0);
		   }
	});
	$('#iconSearch').click(function() {
		reLoadData(0);
	});
	$('#iconReset').click(function() {
		$('#tb-auctionType').combobox('clear');
		$('#tb-subCompanyAudit').combobox('clear');
		$('#tb-isRecord').combobox('clear');
		$('#tb-inTimeStart').datetimebox('clear');
		$('#tb-inTimeEnd').datetimebox('clear');
		$('#searchText').searchbox('clear');
		reLoadData(0);
	});
}
var fileNameIndex={0:2,1:4,2:8,3:6,4:12,5:0,6:1}
var fileNameList={0:'登记证',1:'行驶证',2:'购置税',3:'购车发票',4:'委托方组织机构代码',5:'车主身份证',6:'委托合同'}
function getProcedureName(key,fileList){
	var flag=-1;
	for(var i=0;i<fileList.length;i++){
		if(fileNameIndex[key]==fileList[i].fileType){
			if(fileList[i].procedureState==0){//入库
				flag=0;
			}else{
				flag=1;
			}
			break;
		}
	}
	return flag;
}
//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#procedureList").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		subCompanyAuditId:$('#tb-subCompanyAudit').combobox('getValue'),
		isRecordId:$('#tb-isRecord').combobox('getValue'),
		inTimeStart: $('#tb-inTimeStart').datetimebox("getValue"),
		inTimeEnd: $('#tb-inTimeEnd').datetimebox("getValue"),
		keywords: $('#searchText').searchbox('getValue').trim()
    });
}
//入库
function addProcedure(auctionId,procedureId) {
	window.location.href="../../html/procedure/addProcedure.html?id="+auctionId+"&procedureId="+procedureId;
}
//出库--弹窗
function openOutofProcedure(procedureId) {
	$.ajax({type: 'post', url:'../../procedure/getOutofProcedure', dataType: "json",
		data:{procedureId : procedureId},
		success:function(data){
			if(data.returnCode=="000000"){
				$('#DIALOG').dialog({
					title: "手续出库申请",
					width: 600,
					height: 700,
					modal: true,
					href: "../../html/procedure/outProcedure.html",
					buttons : [ 
						{id : 'outofProcedureBtn',text : '申请出库',handler : function() {
							outofProcedure("DIALOG");
						}}, 
						{id : 'cancelBtn',text : '取消',handler : function() {
							$('#DIALOG').dialog("close");
						}} 
					],
					onLoad: function () {
						var list=data.entity.fileTypeList;
						if(list!=null&&list.length>0){
							var str='';
							for(var key =0;key<list.length;key++){
								var i=list[key];
								str+='<div class="out-check">'
										+'<input type="checkbox" id="out-cb-'+i+'" name="fileTypes" value='+i+'>'
										+'<label for="out-cb-'+i+'">'+checkBoxList[i]+'</label>'
									+'</div>';
							}
							$("#checkBoxBody").html(str);
						}
						$("#outofProcedure").form('load', data.entity);
					}
				});
			}else if(data.returnCode=="400000"){
				$.messager.alert('提示', "没有要出库的手续");
			}
		},
	});
}
var checkBoxList={0:'车主身份证',1:'委托合同',2:'车辆登记证',3:'进口车商检证',4:'车辆行驶证',5:'交强险单凭证',6:'购车发票',7:'交裁证明复印件',8:'购置附加税证',9:'进口车报关单',10:'工商认证章',11:'其他证件',12:'委托方组织机构代码证',}
//出库申请
function outofProcedure(_dialogId){
	if (!$('#outofProcedure').form("validate")) {
		return;
	}
	var ff = "";
	ff = $('#outofProcedure input,#outofProcedure textarea').serialize();
	$.ajax({
		type : 'post',
		url : '../../procedure/outofProcedure',
		dataType : 'json',
		data : ff,
		success : function(data) {
			if (data.returnCode == "000000") {
				$.messager.alert('提示','申请出库成功','info',function(){
					window.location.href="../../html/procedure/procedureList.html";	
					$('#' + _dialogId).dialog("close");
				});
			}else {
				$.messager.alert('提示', "申请出库失败");					
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
	});
}

//手续出库申请审核
function outProcedureAudit(auctionId,procedureId) {
	window.location.href="../../html/procedure/outProcedureAudit.html?id="+auctionId+"&procedureId="+procedureId;
}
//手续完结归档
function getProcedureRecord(auctionId,procedureId){
	window.location.href="../../html/procedure/procedureRecordDetail.html?id="+auctionId+"&procedureId="+procedureId;
}