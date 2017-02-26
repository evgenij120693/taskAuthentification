function add_client() {
    var Form = $('form').serialize();
    $.ajax({
        type: 'POST',
        url: 'add_client',
        data: Form,
        success: function (data) {
           alert(data);
        }

    });
}