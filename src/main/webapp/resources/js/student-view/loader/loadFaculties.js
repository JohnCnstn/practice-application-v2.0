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