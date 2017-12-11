$( document ).ready(function() {

    // SUBMIT FORM
    $("#studentOnPracticeForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        var practicesId = [];

        practicesId.push($("#practiceId").val());

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postStudentOnPractice",
            data: JSON.stringify(practicesId),
            dataType: 'json',
            success: function (result) {
                $('#studentOnPracticeModal').modal('hide');
                console.log(result);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
});