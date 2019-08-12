/**
 * _readonly true:只读
 */
function initWareCombobox(_readonly){
	$('#addWarehouse .cityBox').combobox({
		editable : false,
		width:150,
		readonly:_readonly
	});
	$('#addWarehouse .areaBox').combobox({
		editable : false,
		width:150,
		readonly:_readonly
	});
	/*车牌位置*/
	initAddressPCA('#addWarehouse','#ccProvince','#ccCity','#ccArea',_readonly);
	/*钥匙位置*/
	initAddressPCA('#addWarehouse','#kProvince','#kCity','#kArea',_readonly);
	/*库存位置*/
	initAddressPCA('#addWarehouse','#sProvince','#sCity','#sArea',_readonly);
	/*起始地*/
	initAddressPC('#addWarehouse','#originProvince','#originCity',_readonly);
	/*目的地*/
	initAddressPC('#addWarehouse','#destinationProvince','#destinationCity',_readonly);
	//正常钥匙spareKey
	$('#key').combobox({
		multiple : false,
		editable : false,
		readonly:_readonly,
		data :[{"id": "无","value": "无"},{"id": "一把","value": "一把"}, {"id": "两把","value": "两把"}],
		valueField:'id',
		textField:'value',
		panelHeight : "auto"
	});
	//备用钥匙
	$('#spareKey').combobox({
		multiple : false,
		editable : false,
		readonly:_readonly,
		data :[{"id": "无","value": "无"},{"id": "一把","value": "一把"}, {"id": "两把","value": "两把"}],
		valueField:'id',
		textField:'value',
		panelHeight : "auto"
	});
	//应支付给
	$('#trailerCostPay').combobox({
		multiple : false,
		editable : false,
		readonly:_readonly,
		data:[
		    {"id": "国投","value": "国投"},
		    {"id": "车主","value": "车主"},
		    {"id": "4S店","value": "4S店"},
		    {"id": "停车场","value": "停车场"},
		    {"id": "拖车公司","value": "拖车公司"},
		    {"id": "其他","value": "其他"}
		],
		valueField:'id',
		textField:'value',
		panelHeight : "auto"
	});
	// 入库时间
	$('#storeTime').datetimebox({
		required : false,
		editable : false,
		readonly:_readonly
	});
}
/*查看详情--车辆图片展示*/
function loadStockPhoto(imglist){
	var imgCount=0;
	var trid=0;
	for(var i=0;i<imglist.length;i++){
		var img=imglist[i].imgPath;
		if(imgCount%8==0){
			trid++;
			$("#warehousePhoto").append("<tr col='cur-"+trid+"'></tr>");
		}
		$("#warehousePhoto tr[col='cur-"+trid+"']").append("<img src='"+img+"' border='0' width='100' height='100'>");
		imgCount++;
	}
}
/**
 * 查看详情
 */
function initWarehouseData(_id,auction,warehouse){
	$("#addWarehouse").form('load', warehouse);
	$("#addWarehouse input,#addWarehouse textarea").attr("readonly", true);
	//图片展示
	loadStockPhoto(warehouse['warehouseFileList']);
	$("#baseInfo").form('load', auction);
	$("#baseInfo input,#baseInfo textarea").attr("readonly", true);
	$("#addWarehouse input[type='checkbox'],#addWarehouse input[type='radio']").click(function(){
		return false;
	});
}