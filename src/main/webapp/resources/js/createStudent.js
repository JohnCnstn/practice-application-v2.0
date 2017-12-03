$( document ).ready(function() {

    // SUBMIT FORM
    $("#createStudentForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            firstName : $("#studentFirstName").val(),
            lastName : $("#studentLastName").val(),
            userName : $("#studentUserName").val(),
            email : $("#studentEmail").val(),
            password : $("#studentPassword").val(),
            specialityId : $("#specialityId").val(),
            avgScore : $("#avgScore").val(),
            budget : $("#budget").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postStudent",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (result) {
                if (result.status == "OK") {
                    $("#postResultDiv").html("<strong>Success</strong>");
                } else {
                    $("#postResultDiv").html("<strong>Error</strong>");
                }
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
        $("#studentFirstName").val("");
        $("#studentLastName").val("");
        $("#studentUserName").val("");
        $("#studentEmail").val("");
        $("#studentPassword").val("");
        $("#specialityId").val("");
        $("#avgScore").val("");
        $("#budget").val("");
    }
});
