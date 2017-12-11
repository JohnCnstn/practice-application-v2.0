$( document ).ready(function() {

    // SUBMIT FORM
    $("#deleteStudentFromPracticeForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        var practicesId = [];

        practicesId.push($("#practiceId").val());

        $.ajax({
            type: "DELETE",
            contentType: "application/json",
            url: window.location + "/deleteStudentFromPractice",
            data: JSON.stringify(practicesId),
            dataType: 'json',
            success: function (result) {
                $('#deleteStudentFromPracticeModal').modal('hide');
                console.log(result);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
});