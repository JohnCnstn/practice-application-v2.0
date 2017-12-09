$( document ).ready(function() {

    // SUBMIT FORM
    $("#studentOnPracticeForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            practicesId : $("#practiceId").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postStudentOnPractice",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (result) {
                $('#universityModal').modal('hide');
                console.log(result);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
});