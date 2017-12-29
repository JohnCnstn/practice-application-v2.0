$( document ).ready(function() {

    var specialitiesList = '';

    // GET REQUEST
    $("#getAllSpecialities").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/getAllSpecialities",
            success: function (result) {
                var len = result.length;

                if (len > 0) {
                    $(".submitStudent").prop('disabled', false);
                } else {
                    callErrorAlert("You should create at least 1 faculty!");
                    $(".submitStudent").prop('disabled', true);
                }
                if (specialitiesList.length == 0) {
                    for (var i = 0; i < len; i++) {
                        specialitiesList += '<option value="' + result[i].id + '">' + result[i].name + '</option>';
                    }
                    $('select#specialityId').append(specialitiesList);
                    console.log("Success: ", result);
                }
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});