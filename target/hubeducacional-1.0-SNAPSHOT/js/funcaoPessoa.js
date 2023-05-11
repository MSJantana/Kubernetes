$(document).ready(function () {
    $("tr #deletePessoa").click(function (e) {
        e.preventDefault();
        var cod = $(this).parent().find('#codigo').val();
        swal({
            title: "Está Seguro de Eliminação?",
            text: "Uma Vez Removido Deve ser Adicionado Novamente!",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Sim, Eliminar!",
            cancelButtonText: "Não, Cancelar!",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        eliminarUsuario(cod);
                        swal("Excluido!", "O Usuário foi Removido!!!", "success");
                        setTimeout(function () {
                            parent.location.href = "Pessoa"
                        }, 1800);
                    } else {
                        swal("Cancelado", "Eliminação Cancelada", "error");
                    }
                });
    });

    function eliminarUsuario(cod) {
        var url = "Pessoa?action=eliminarPessoa&cod=" + cod;
        console.log("eliminado");
        $.ajax({
            type: 'POST',
            url: url,
            async: true,
            success: function (r) {

            }
        });
    }
});

