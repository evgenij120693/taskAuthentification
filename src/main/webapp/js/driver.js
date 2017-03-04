$(document).ready(function () {
    $("#updateStatus").onclick(function () {
        var val =$(this).val();
        $.ajax({
            type: 'POST',
            url: 'update_status',
            data: val,
            success: function (data) {
                alert(data);
            }

        });
    });
})
