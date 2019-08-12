$(function() {
	var auctionSetId = getParamValue("auctionSetId");
	var type = getParamValue("type");
	$('#tabs-div').tabs('select',parseInt(type));
	$.post('../../aftersale/getApplyByAuctionSetId',{
		auctionSetId:auctionSetId
	},function(data) {
		if(data.returnCode=='000000'){
			var defer=data.entity.defer;
			var adjustPrice=data.entity.adjustPrice;
			var backCar=data.entity.backCar;
			if(defer!=null){
				$("#deferForm").form('load', defer);
				loadImgInfo('#deferPhoto',defer['fileList']);
			}else{
				$('.deferBtn').hide();
			}
			if(adjustPrice!=null){
				$("#adjustPriceForm").form('load', adjustPrice);
				loadImgInfo('#adjustPricePhoto',adjustPrice['fileList']);
				if (adjustPrice['carPriceType'] == '车款百分比') {
					$("#cpt-type").html("%");
				} else if (adjustPrice['carPriceType'] == '固定值') {
					$("#cpt-type").html("元");
				}
				if (adjustPrice['commissionType'] == '佣金百分比') {
					$("#ct-type").html("%");
				} else if (adjustPrice['commissionType'] == '固定值') {
					$("#ct-type").html("元");
				}
			}else{
				$('.adjustPriceBtn').hide();
			}
			if(backCar!=null){
				$("#backCarForm").form('load', backCar);
				loadImgInfo('#backCarPhoto',backCar['fileList']);
			}else{
				$('.backCarBtn').hide();
			}
			$("#deferForm input,#deferForm textarea").attr("readonly", true);
			$("#adjustPriceForm input,#adjustPriceForm textarea").attr("readonly", true);
			$("#backCarForm input,#backCarForm textarea").attr("readonly", true);
			$("input[type='checkbox'],input[type='radio']").click(function(){
				return false;
			});
		}
	});
});
/**
 * type 0:延期 1:调价 2:退货
 * isPass 1:通过 -1:驳回
 */
function audit(_type,isPass){
	var applicationType='';
	var applicationId='';
	switch (_type) {
	case 0:
		applicationType = "延期";
		applicationId=$('#deferForm input[name="id"]').val();
		break;
	case 1:
		applicationType = "调价";
		applicationId=$('#adjustPriceForm input[name="id"]').val();
		break;
	case 2:
		applicationType = "退货";
		applicationId=$('#backCarForm input[name="id"]').val();
		break;
	}
	$.post('../../aftersale/addAbnormalAudit',{
		applicationType:applicationType,
		applicationId:applicationId,
		isPass:isPass
	},function(data) {
		if(data.returnCode=='000000'){
			$.messager.alert('提示', "审核成功",'info',function(){
				switch (_type) {
				case 0:
					$('.deferBtn').hide();
					$('.deferAfter').show();
					if(isPass==0){
						$('.deferAfter button').text('审核已通过');
					}else{
						$('.deferAfter button').text('审核已驳回');
					}
					break;
				case 1:
					$('.adjustPriceBtn').hide();
					$('.adjustPriceAfter').show();
					if(isPass==0){
						$('.adjustPriceAfter button').text('审核已通过');
					}else{
						$('.adjustPriceAfter button').text('审核已驳回');
					}
					break;
				case 2:
					$('.backCarBtn').hide();
					$('.backCarAfter').show();
					if(isPass==0){
						$('.backCarAfter button').text('审核已通过');
					}else{
						$('.backCarAfter button').text('审核已驳回');
					}
					break;
				}
			});
		}
	});
}
/*图片展示*/
function loadImgInfo(_div,imglist){
	var imgCount=0;
	var trid=0;
	for(var i=0;i<imglist.length;i++){
		var img=imglist[i].imgPath;
		if(imgCount%8==0){
			trid++;
			$(_div).append("<tr col='cur-"+trid+"'></tr>");
		}
		$(_div+" tr[col='cur-"+trid+"']").append("<img src='"+img+"' border='0' width='100' height='100'>");
		imgCount++;
	}
}
