$( document ).ready(function() {

    // SUBMIT FORM
    $("#universityForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            name : $("#universityName").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/postUniversity",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (data) {
                $('#universityModal').modal('hide');
                callSuccessAlert('You created the university!');
                console.log(data);
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
        $("#universityName").val("");
    }
});



$( document ).ready(function() {

    // GET REQUEST
    $("#getAllHeadMasters").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/getAllHeadMasters",
            success: function (result) {
                var headMasterList = '';
                var len = result.length;
                for(var i=0; i<len; i++){
                    headMasterList += '<option value="' + result[i].id + '">' + result[i].userName + '</option>';
                }
                $('select#headMasterId').append(headMasterList);
                console.log("Success: ", result);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});