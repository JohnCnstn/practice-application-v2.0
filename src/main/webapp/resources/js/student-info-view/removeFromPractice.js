$(document).ready(function() {
    var removeTable =  $('#removeFromPracticeTable').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": false,
        "autoWidth": false,
        "colReorder": true
    });

    var removeFromPracticeList = [];

    $('#removeFromPracticeTable tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );

    $("#deleteStudentFromPracticeForm").submit(function(event) {

        var elements = document.getElementsByClassName('selected');

        for (var i = 0; i < elements.length; i++) {
            var element = elements[i];
            removeFromPracticeList.push(element.id);
        }

        $(".selected").removeClass("selected");

        event.preventDefault();
        removeFromPractice(removeFromPracticeList);
    });

    function removeFromPractice(removeFromPracticeList){
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/removeFromPractice",
            data: JSON.stringify(removeFromPracticeList),
            dataType: 'json',
            success: function (result) {
                $('#deleteStudentFromPracticeModal').modal('hide');
                callDeleteAlert('You released student from practices!');
                console.log(result);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });

        resetData();
    }

    function resetData(){
        removeFromPracticeList = [];
    }
} );