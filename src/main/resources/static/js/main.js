
(function ($) {
    $('.qr-input').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })

    var form_in = $('.validate-input .qr-input');
    $('.validate-form').on('submit',function(){
        var flag = true;
        for(var i=0; i<form_in.length; i++) {
            if(validate(form_in[i]) == false){
                show_valid(form_in[i]);
                flag = false;
            }
        }
        return flag;
    });


    $('.validate-form .qr-input').each(function(){
        $(this).focus(function(){
           hide_valid(this);
        });
    });

    function show_valid(form_in) {
        var alt = $(form_in).parent();
        $(alt).addClass('alert-validate');
    }

    function hide_valid(form_in) {
        var alt = $(form_in).parent();
        $(alt).removeClass('alert-validate');
    }
    
    
})(jQuery);