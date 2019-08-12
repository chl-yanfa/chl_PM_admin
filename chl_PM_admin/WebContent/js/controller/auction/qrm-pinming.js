$(function () {
	
  var carsType={}  
  var carList1Str = '';

  function ajax(id,type) {
    $.get('https://www.guotouhulian.com/api/carModelTypes.json',{id:id,type:type},function(res){
    	if(type==0){
    		$.each(res, function(index, item) {
    	        carList1Str += '<div class="disabled center">' + item.name + '</div>';
    	
    	        $.each(item.items, function(ind, val) {
    	          carList1Str += '<div class="item1" id=' + val.id + '>' + val.name + '</div>';
    	        })
    	    })
            $('.one .content').append($(carList1Str));
    	}else if(type==1){
    		var carList2Str = '';
    	    $.each(res, function(index, item) {
    	      carList2Str += '<div class="disabled center">' + item.name + '</div>';
    	      $.each(item.items, function(ind, val) {
    	        carList2Str += '<div class="item2" id=' + val.id + '>' + val.name + '</div>';
    	      })
    	    })
    	    $('.two .content').append($(carList2Str));
    	    $('.two').show();
    	}else{
    		var carList3Str = '';
    	    $('.qrm-input').val('');
    	    $.each(res, function(index, item) {
    	      carList3Str += '<div class="disabled center">' + item.name + '</div>';
    	      $.each(item.items, function(ind, val) {
    	        carList3Str += '<div class="item3" id=' + val.id + '>' + val.name + '</div>';
    	      })
    	    })
    	    $('.three .content').append($(carList3Str));
    	    $('.three').show();
    	}
	})
  
  }

  ajax(0,0);
  $('.select-cy').on('click', '.item1', function() {
    $(this).addClass('active').siblings().removeClass('active');
    $('.two .content').html('');
    $('.three').hide();
    
    $('.qrm-input').val('');

    ajax($(this).attr('id'),1)
    
  })
  $('.select-cy').on('click', '.item2', function() {
    $(this).addClass('active').siblings().removeClass('active');
    $('.three .content').html('');

    ajax($(this).attr('id'),2)
    
  })
  $('.select-cy').on('click', '.item3', function(e) {
	  e.stopPropagation();
    var val='';
    
    $(this).addClass('active').siblings().removeClass('active');
    val=$('#drivingBrand').find('.active').text()+' / '+$('#drivingSystem').find('.active').text()+' / '+$('#drivingModel').find('.active').text();
    carsType={};
    carsType.pinpai=$('#drivingBrand').find('.active').text();
    carsType.chexi=$('#drivingSystem').find('.active').text();
    carsType.chexing=$('#drivingModel').find('.active').text();
    carsType.pinpaiid=$('#drivingBrandId').find('.active').attr('id');
    carsType.chexiid=$('#drivingSystemId').find('.active').attr('id');
    carsType.chexingid=$('#drivingModelId').find('.active').attr('id');
    
    $('#fullName').val(val);
    $('#fullShow').val(val);
    
    $('.select-cy').hide()
    
  })
  $('.english').on('click', '.english-item', function() {

    var eng = $(this).text();


    $.each($('.one').find('.disabled'), function(index, item) {

      if ($(item).text() == eng) {
        $(item).parent().scrollTop($(item).attr('scroll'));
      }
    })
  })
  $('body').on('click', function(e) {
    if ($(e.target).parents('.select-cy').hasClass('select-cy')) { //如果点击自身则不做处理否则隐藏
      return;
    }
    if ($('.select-cy').css('display') == 'block') {
      $('.select-cy').hide();
    }
  })
  $('.qrm-pinming').on('click', function(e) {

    e.stopPropagation();
    $('.select-cy').show();
    $.each($('.one .content').find('.disabled'),function(index,item){
      $(item).attr('scroll',$(item).position().top-$(item).height());
    })
  })
	
    
});