var _ctx = $("meta[name='ctx']").attr("content");
var table;
var tabled_changed = false;
$(document).ready(function () {

    setMenuActive("users-list");

    table = $('#userslist').DataTable({
        // dom : 'Bfrtip',
        dom: "<'row'<'col-sm-6'B>>"
                + "<'row'<'col-sm-12'tr>>"
                + "<'row'<'col-sm-5'i><'col-sm-7'p>>",
        responsive: true,
        buttons: [{
                extend: 'csvHtml5',
                filename: 'usersList',
                title: 'usersList',
                exportOptions: {
                    columns: [0, 1, 2, 3, 4]
                }
            }, {
                extend: 'excelHtml5',
                filename: 'usersList',
                title: 'usersList',
                exportOptions: {
                    columns: [0, 1, 2, 3, 4]
                }
            }, {
                extend: 'pdfHtml5',
                filename: 'usersList',
                title: 'usersList',
                exportOptions: {
                    columns: [0, 1, 2, 3, 4]
                }
            }],
        ajax: {
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            type: "POST",
            url: _ctx + "/users/restlist",
            dataSrc: function (json) {
                return json;
            }
        },
        columns: [
            {data: 'id'},
            {data: 'firstname'},
            {data: 'lastname'},
            {data: 'email'},
            {data: 'role'},
            {render: function (data, type, row) {
                    return '<a class="btn btn-sm btn-default" href="javascript:void(0)" title="Edit" onclick="javascript:edit_user('
                            + row.id
                            + ');"><i class="fa fa-pencil"></i></a>'
                            + '&nbsp;<a class="btn btn-sm btn-default" href="javascript:void(0)" title="Change Password" onclick="javascript:open_changepassword('
                            + row.id
                            + ',\''
                            + row.email
                            + '\');"><i class="fa fa-lock"></i></a>'
                            + '&nbsp;<a class="btn btn-sm btn-default" href="javascript:void(0)" title="Delete" onclick="javascript:open_delete('
                            + row.id
                            + ',\''
                            + row.email
                            + '\');"><i class="fa fa-trash-o"></i></a>';
                },
                orderable: false
            }
        ]
    });

    table.buttons().container().appendTo('#userslist_wrapper .col-sm-6:eq(0)');

    $('#modal_form').on('hide.bs.modal', function () {
        if (tabled_changed)
            close();
    });

    $('#modalDelete_form').on('hide.bs.modal', function () {
        $('#delEmail').empty();
        $('#delId').empty();
        $('#msgsDel').empty();
    });

    $("input[type=password]").keyup(function () {
        $('#msgsCp').empty();
        $("#btnChangePassword").prop("disabled", true);
        if (($("#passwordcp").val().trim().length >= 3)) {
            if ($("#passwordcp").val() === $("#passwordcp1").val()) {
                $("#btnChangePassword").prop("disabled", false);
            }
        }
    });

});// fine ready

function add_user() {
    save_method = 'add';
    $('#form')[0].reset(); // reset form on modals
    $('[name="email"]').prop("readonly", false);
    $('#btnSave').prop("disabled", false);
    $('.form-group').removeClass('has-error'); // clear error class
    $('.help-block').empty(); // clear error string
    $('#modal_form').modal({backdrop: 'static', keyboard: false}); // show bootstrap modal
    $('.password_group').show(); // show password block
    $('#form_password').val(''); // show password block
    $('.modal-title').text('Add User'); // Set Title to Bootstrap modal title
    $('#msgs').empty();
    tabled_changed = false;
}

function edit_user(id) {
    save_method = 'update';
    $('#form')[0].reset(); // reset form on modals
    $('#btnSave').prop("disabled", false);
    $('.form-group').removeClass('has-error'); // clear error class
    $('.help-block').empty(); // clear error string
    $('#form_password').val('password'); // show password block
    $('.password_group').hide(); // show password block
    $('#msgs').empty();
    tabled_changed = false;
    $.ajax({
        url: _ctx + "/users/restgetUser?id=" + id,
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            for (var i in data) {
                $('[name="' + i + '"]').val(data[i]);
            }
            $('#modal_form').modal({backdrop: 'static', keyboard: false});
            $('.modal-title').text('Edit User');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Error get data from ajax');
        }
    });
}

function reload_table() {
    table.ajax.reload(null, false); // reload datatable ajax
}

function close() {
    $('#modal_form').modal();
    table.ajax.reload(null, false); // reload datatable ajax
}

function save() {

    $('#btnSave').text('saving...'); // change button text
    $('#btnSave').attr('disabled', true); // set button disable
    tabled_changed = false;
    var url;

    if (save_method == 'add') {
        url = _ctx + "/users/restNewUser";
    } else {
        url = _ctx + "/users/restUpdateUser";
    }

    // ajax adding data to database
    $.ajax({
        url: url,
        type: "POST",
        data: $('#form').serialize(),
        dataType: "JSON",
        success: function (data) {
            $("#msgs").empty();
            // if(data>0) //if success close modal and reload ajax table
            // {
            // $('#modal_form').modal('hide');
            // reload_table();
            // }
            if (data) {
                $.each(data, function (index, msg) {
                    var classs = 'alert alert-info';
                    if (msg.type == 'INFO')
                        classs = 'alert alert-success';
                    else if (msg.type == 'ERROR')
                        classs = 'alert alert-danger';
                    var div = $('<div class="' + classs + '"><strong>'
                            + msg.type + '</strong>: ' + msg.text
                            + ' </div>"');
                    $("#msgs").append(div);
                });
            }

            $('#btnSave').text('Save'); // change button text
            tabled_changed = true;
            if (save_method != 'add')
                $('#btnSave').attr('disabled', false); // set button enable
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Error adding / update data');
            $('#btnSave').text('Save'); // change button text
            $('#btnSave').attr('disabled', false); // set button enable

        }
    });
}

function open_delete(id, email) {
    $("#modalDelete_form .modal-title").text("Delete User");
    $("#delContent").show();
    $('#btnDelete').show(); // set button enable
    $('#delEmail').text(email); // change button text
    $('#delId').val(id);
    $('#modalDelete_form').modal({backdrop: 'static', keyboard: false}); // show bootstrap modal
    $('#msgsDel').empty();
}

function delete_user() {
    var id = $('#delId').val();
    $.ajax({
        url: _ctx + "/users/restDeleteUser?id=" + id,
        type: "POST",
        dataType: "JSON",
        success: function (data) {
            $("#msgsDel").empty();
            if (data) {
                $.each(data, function (index, msg) {
                    var classs = 'alert alert-info';
                    if (msg.type == 'INFO')
                        classs = 'alert alert-success';
                    else if (msg.type == 'ERROR')
                        classs = 'alert alert-danger';
                    var div = $('<div class="' + classs + '"><strong>'
                            + msg.type + '</strong>: ' + msg.text
                            + ' </div>"');
                    $("#msgsDel").append(div);
                    $("#delContent").hide();

                });
            }

            $('#btnDelete').hide(); // set button enable
            reload_table();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Error deleting data');
        }
    });

}

function open_changepassword(id, email) {
    $('#btnDelete').show(); // set button enable
    $('#pwdText').show();
    $('#cpEmail').text(email); // change button text
    $('#cpId').val(id);
    $('#modalCp_form').modal('show'); // show bootstrap modal
    $('#msgsCp').empty();
    $('#passwordcp').val('');
    $('#passwordcp1').val('');

}
function update_password() {
    var id = $('#cpId').val();
    var password = $('#passwordcp').val();
    $.ajax({
        url: _ctx + "/users/updatePassword",
        type: "POST",
        dataType: "JSON",
        data: {
            id: id,
            passw: password 
        },
        success: function (data) {
            $("#msgsCp").empty();
            if (data) {
                writeMsgs(data, "msgsCp");
                $('#passwordcp').val('');
                $('#passwordcp1').val('');
                $("#btnChangePassword").prop("disabled", true);
            }
            $('#pwdText').hide();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            writeMsgsError("Error deleting data", "msgsCp")
        }
    });
}
