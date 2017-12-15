$(document).ready(function() {
    var table =  $('#example1').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": false,
        "autoWidth": false,
        "colReorder": true
    });

    var studentList='';

    $('#example1 tbody').on( 'click', 'tr', function () {
        studentList += $(this).data("toggle");
        $(this).toggleClass('selected');
    } );

    $('#assignButton').click( function () {
        alert( studentList );
        // alert( table.rows('.selected').data().length +' row(s) selected' );
    } );
} );