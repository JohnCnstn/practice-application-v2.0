$( document ).ready(function() {

    // SUBMIT FORM
    $("#headMasterForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            firstName : $("#firstName").val(),
            lastName : $("#lastName").val(),
            userName : $("#userName").val(),
            email : $("#email").val(),
            password : $("#password").val(),
            companyId : $("#companyId").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postHeadMaster",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (result) {
                $('#headMasterModal').modal('hide');
                callSuccessAlert('You created head master!');
                console.log(result);
            },
            error: function (e) {
                callErrorAlert(e.responseText);
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();
    }

    function resetData(){
        $("#firstName").val("");
        $("#lastName").val("");
        $("#userName").val("");
        $("#email").val("");
        $("#password").val("");
    }
});
