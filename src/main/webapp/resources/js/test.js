var editor;

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
        ajax: {
            "url": window.location + "/postStudentOnPractice",
            "type": "POST"
        },
        table: "#example",
        fields: [ {
            label: "First name:",
            name: "firstName"
        }, {
            label: "Last name:",
            name: "lastName"
        }, {
            label: "University:",
            name: "university"
        }, {
            label: "Faculty:",
            name: "faculty"
        }, {
            label: "Is budget:",
            name: "isBudget"
        }, {
            label: "Average score:",
            name: "avgScore"
        }, {
            label: "Status:",
            name: "status"
        }
        ]
    } );

    var table = $('#example').DataTable( {
        lengthChange: false,
        ajax: {
            "url": window.location + "/postStudentOnPractice",
            "type": "POST"
        },
        columns: [
            { data: null, render: function ( data, type, row ) {
                // Combine the first and last names into a single table field
                return data.firstName+' '+data.lastName;
            } },
            { data: "position" },
            { data: "office" },
            { data: "extn" },
            { data: "start_date" },
            { data: "salary", render: $.fn.dataTable.render.number( ',', '.', 0, '$' ) }
        ],
        select: true
    } );

    // Display the buttons
    new $.fn.dataTable.Buttons( table, [
        { extend: "create", editor: editor },
        { extend: "edit",   editor: editor },
        { extend: "remove", editor: editor }
    ] );

    table.buttons().container()
        .appendTo( $('.col-sm-6:eq(0)', table.table().container() ) );
} );