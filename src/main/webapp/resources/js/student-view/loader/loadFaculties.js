$( document ).ready(function() {

    // GET REQUEST
    $("#getAllFaculties").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url: window.location + "/getAllFaculties",
            success: function (result) {
                var facultiesList = '';
                var len = result.length;
                if (len > 0) {
                    $(".submitSpeciality").prop('disabled', false);
                } else {
                    callErrorAlert("You should create at least 1 faculty!");
                    $(".submitSpeciality").prop('disabled', true);
                }
                for(var i=0; i<len; i++){
                    facultiesList += '<option value="' + result[i].id + '">' + result[i].name + '</option>';
                }
                $('select#facultyId').append(facultiesList);
                console.log("Success: ", result);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
    }
});