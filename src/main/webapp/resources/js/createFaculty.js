$( document ).ready(function() {

    // SUBMIT FORM
    $("#facultyForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            name : $("#facultyName").val(),
            universityId : $("#universityId").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postFaculty",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (result) {
                $('#facultyModal').modal('hide');
                console.log(result);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();
    }

    function resetData(){
        $("#facultyName").val("");
    }
});
