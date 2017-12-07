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
            success: function (data) {
                if (data.status === "OK") {
                    $("#postResultDiv").html("<strong>Success</strong>");
                } else {
                    $("#postResultDiv").html("<strong>Error</strong>");
                }
                console.log(data);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
});
