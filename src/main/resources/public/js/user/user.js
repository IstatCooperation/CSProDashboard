var _ctx = $("meta[name='ctx']").attr("content");
$(document).ready(function () {

    $("input[type=password]").keyup(function () {
        $('#msgs').empty();
        $("#btnChangePassword").prop("disabled", true);
        if (($("#password").val().trim().length >= 3)) {
            if ($("#password").val() == $("#password1").val()) {
                $("#btnChangePassword").prop("disabled", false);
            }
        }
    });

    $('#collapse1').on('hide.bs.collapse', function () {
        $('#password').val('');
        $('#password1').val('');
        $('#msgs').empty();
    });
});

function update_mypassword() {
    var password = $('#password').val();
    $.ajax({
        url: _ctx + "/users/updateMyPassword",
        type: "POST",
        dataType: "JSON",
        data: {
            'passw': password
        },
        success: function (data) {

            $("#msgs").empty();

            if (data) {
                writeMsgs(data, "msgs");
                $('#password').val('');
                $('#password1').val('');
                $("#btnChangePassword").prop("disabled", true);
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            writeMsgsError('Error deleting data', 'msgs');
        }
    });
}

