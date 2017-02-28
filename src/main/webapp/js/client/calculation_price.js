$(document).ready(function () {

    $( "input" ).change(function() {
        console.log("change");
        var flag = false;
        $("input[type=text]").each(function () {
            console.log($(this).val());
            if($(this).val()=="" && !flag){
                flag = true;
            }
        });
        if(!flag){
            calculate();
            $("#tr_price").fadeIn("fast");
            $("#submit").fadeIn("fast");
        }else{

            $("#tr_price").fadeOut("fast");
            $("#submit").css("display","none");
            $("#price").val("0");
        }

    });


    function calculate() {
        var price = Math.floor(Math.random() * (500 - 100) + 100);
        $("#price").val(price);

    }
});




