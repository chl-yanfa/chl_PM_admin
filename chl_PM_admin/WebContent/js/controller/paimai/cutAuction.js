$(function() {
	loadData();
	loadAuctionData();
	addCjTable();
	addAuctionTable();
	$("#dealButton").click(function(){
		submitDeal();
	})
});
var paimaiInfo=null;
function loadData(){
	var auctionSetId = getParamValue("auctionSetId");
	var auctionId = getParamValue("auctionId");
	$.post('../../paimai/getPaimaiInfo',{
		auctionSetId:auctionSetId
	},function(res) {
		paimaiInfo=res.entity;
		if(paimaiInfo!=null){
			$(".paimaiInfo-div").find("span[col]").each(function(){
				$(this).html(paimaiInfo[$(this).attr("col")]);
			});
		}
	});
	$.post('../../auctionResult/getToDealCost',{
		auctionSetId:auctionSetId,
		auctionId:auctionId
	},function(res) {
		data=res.entity;
		if(data!=null){
			$(".set-money").find("td[col]").each(function(){
				$(this).html(data[$(this).attr("col")]);
			});
		}
	});
}
function addCjTable(){
	//auctionSetId
	var auctionSetId = getParamValue("auctionSetId");
	$.post('../../offerwater/getOfferWaterList',{
		auctionSetId:auctionSetId
	},function(res) {
		if(res.returnCode=='000000'){
			var str='';
			for(var i=0;i<res.rows.length;i++){
				var cjInfo = res.rows[i];
				var name = cjInfo.realname;
				if(cjInfo.realname == null){
					name = cjInfo.nickname;
				}
				str+='<tr>'+
				'<td>'+name+'</td>'+
				'<td>'+cjInfo.bidTime+'</td>'+
				'<td>'+cjInfo.bidAmount+'</td>'+
				'</tr>';
			}
			$('.cjTable').append(str);
		}
	});
	$.post('../../offerwater/getBidCountByOrderId',{
		auctionSetId:auctionSetId
	},function(res) {
		if(res.returnCode=='000000'){
			$('#bidCount').html(res.entity);
		}
	});
}
var highestCustomer=null;
function addAuctionTable(){
	var auctionSetId = getParamValue("auctionSetId");
	$.post('../../offerwater/getHighestCustomerByOrderId',{
		auctionSetId:auctionSetId
	},function(res) {
		if(res.returnCode=='000000'&&res.entity!=null){
			var str='';
			var info = res.entity;
			var name = info.realname;
			if(info.realname == null){
				name = info.nickname;
			}
			str+='<tr>'+
			'<td>'+name+'</td>'+
			'<td>'+info.phone+'</td>'+
			'<td>'+info.maximumPrice+'</td>'+
			'</tr>';
			$('.auctionTable').append(str);
			highestCustomer=info;
			$('#highestPrice').html(info.maximumPrice);
		}
	});
}

function submitDeal(){
	$("#dealButton").attr('disabled',true); 
	var auctionId = getParamValue("auctionId");
	var auctionSetId = getParamValue("auctionSetId");
	if(highestCustomer==null){
		$.messager.alert('提示','当前无最高出价','info');
		return;
	}
	var mid = highestCustomer.mid;
	var phone = highestCustomer.phone;
	var fullName = paimaiInfo.fullName; 
	$.post('../../auctionResult/confirm',{
		auctionId:auctionId,
		orderId:auctionSetId,   
		mid:mid,
		phone:phone
	},function(res) {
		if(res.returnCode=='000000'){
			$.messager.alert('提示', "拍品已成交！",'info',function(){
				var paimaiId = getParamValue("paimaiId");
				window.location.href="../../html/paimai/paimaiPublish.html?id="+paimaiId;
				$("#dealButton").attr('disabled',false);
			});
		}else{
			$.messager.alert('提示', res.returnMsg,'info');
			$("#dealButton").attr('disabled',false);
		}
	});
}
function loadAuctionData(){
	var auctionId = getParamValue("auctionId");
	var auctionSetId = getParamValue("auctionSetId");
	$("#auctionSetBody").load("../auction/auctionSetTemp.html",function(){
		$("#vehicleBody").load("../auction/auctionTemp.html",function(){
			$.post("../../auction/getVPWById",{
				id : auctionId,
			},function(res) {
				var auction=res.entity.auction;
				loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
				
				$.post('../../auctionSet/getAuctionSetById',{
					auctionSetId:auctionSetId
				},function(res) {
					var auctionSet=res.entity;
					$("#auctionSetDiv table").find("span[col]").each(function(){
						$(this).html(auctionSet[$(this).attr("col")]);
					});
				});
			});
		});
	});
}
