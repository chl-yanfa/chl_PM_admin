$(function() {
	loadData();
	loadAuctionData();
	addCjTable();
});
function loadData(){
	var auctionSetId = getParamValue("auctionSetId");
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
	$.post('../../aftersale/getDealInfoByAuctionSetId',{
		auctionSetId:auctionSetId
	},function(res) {
		var dealInfo=res.entity;
		
		$(".result-div .deal-info").find("span[col]").each(function(){
			$(this).html(dealInfo[$(this).attr("col")]);
		});
	});
}
function addCjTable(){
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
				str+='<tr>'+'<td>'+name+'</td>';
				str+='<td>'+cjInfo.bidTime+'</td>'+
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
function loadAuctionData(){
	var auctionId = getParamValue("auctionId");
	var auctionSetId = getParamValue("auctionSetId");
	$("#auctionSetBody").load("../auction/auctionSetTemp.html",function(){
		$("#vehicleBody").load("../auction/auctionTemp.html",function(){
			$.post("../../auction/getVPWById",{
				id : auctionId,
			},function(res) {
				var auction=res.entity.auction;
				if(auction.auctionType == 0){
					$(".cartemp-div").show();
				}else if(auction.auctionType == 1){
					$(".vpartstemp-div").show();
				}else if(auction.auctionType == 2){
					$(".apartstemp-div").show();
				}
				
				loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
				
				/** 拍卖结果页 start*/
				/**轮播显示 */
				loadAlbumImg(auction['auctionFileList']);
				//拍品信息
				$("#carInfo").html(auction['fullName']);
				$(".car-deal-div").find("span[col]").each(function(){
					$(this).html(auction[$(this).attr("col")]);
				});
				
				/** 拍卖结果页 end*/
				$.post('../../auctionSet/getAuctionSetById',{
					auctionSetId:auctionSetId
				},function(res) {
					var auctionSet=res.entity;
					$(".auctionSetInfo").find("td[col]").each(function(){
						$(this).html(auctionSet[$(this).attr("col")]);
					});
					$("#auctionSetDiv table").find("span[col]").each(function(){
						$(this).html(auctionSet[$(this).attr("col")]);
					});
					$(".priceTable").find("td[col]").each(function(){
						$(this).html(auctionSet[$(this).attr("col")]);
					});
					/** 拍卖结果页 start*/
					if(auctionSet.orderState == '10'){
						$(".result-div .to-auct-info").show();
					}else if(auctionSet.orderState == '20'){
						$(".result-div .auct-ing-info").show();
					}else if(auctionSet.orderState == '30'){
						$(".result-div .auct-ed-info").show();
					}else if(auctionSet.orderState == '40'){
						$(".result-div .fail-info").show();
					}else if (auctionSet.orderState == '50'||auctionSet.orderState == '60'||auctionSet.orderState == '70'||auctionSet.orderState == '80'){
						$(".result-div .deal-info").show();
					}else if(auctionSet.orderState == '-8'){
						$(".result-div .fail-8-info").show();
					}else if(auctionSet.orderState == '-10'){
						$(".result-div .fail-10-info").show();
					}
					/** 拍卖结果页 end*/
				});
			});
		});
	});
}
function loadAlbumImg(imglist){
	if(imglist!= null && imglist.length>0){
		for(var i=0;i<imglist.length;i++){
			var imgPath=imglist[i].imgPath;
			if(imglist[i].fileType==0 && imglist[i].picType==0){
				$("#album_picture_list").append("<li><img data-original='"+imgPath+"' src='"+imgPath+"' alt=''></li>");
			}
		}
		$("#imgTotal").html(imglist.length+"张");
		LoadJS('../../js/controller/paimai/album.min.js');
		$('#album_picture_list img').zoomify();
	}else{
		$('#album-div').html(''+
			'<div style="width: 100%;height:100%;background:#fafafa;margin-top:10px;">'+
				'<h1 style="font-weight: bold;color: #ccc;"'+
					'>暂无图片</h1>'+
			'</div>'+
		'');
	}
}