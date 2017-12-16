function callDeleteAllert(removeFromPracticeList) {
    swal({
        title: "Are you sure?",
        text: "Are you sure that you want to cancel this order?",
        type: "warning",
        showCancelButton: true,
        closeOnConfirm: false,
        confirmButtonText: "Yes, cancel it!",
        confirmButtonColor: "#ec6c62"
    }, function() {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/removeFromPractice",
            data: JSON.stringify(removeFromPracticeList),
            dataType: 'json',
            success: function (result) {
                $('#deleteStudentFromPracticeModal').modal('hide');
                // callSuccessAlert('You removed students from practice!');
                console.log(result);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        })
            .done(function(result) {
                swal("Canceled!", "Your order was successfully canceled!", "success");
            })
            .error(function(result) {
                swal("Oops", "We couldn't connect to the server!", "error");
            });
    });
}