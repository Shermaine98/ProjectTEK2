var isExcel = function (name) {
    return name.match(/xls$/i);
};


$(document).on('submit', '#UploadExcel', function (e) {
    e.preventDefault();
    var file = $('[name="file"]');
    var filename = $.trim(file.val());
    if (!isExcel(filename)) {
        alert('Please Put a Valid Excel 2004?? File');
        return;
    } else {
        $.ajax({
            url: 'ServletsUploadDemo',
            type: 'POST',
            enctype: "multipart/form-data",
            dataType: 'json', 
            data: new FormData(document.getElementById("UploadExcel")),
            processData: false,
            contentType: false,
            success: function (data) {
                $(".table1 div").empty('');
                var i;
                var text= $('<b>Choose the Age by Group Sex Sheet</b> <br />');
                  text.appendTo('#table1');
                $("#ShowSheets").modal("show");
                for (i = 0; i < data.length; i++) {
                    var radioBtn = $('<input type="radio" name="rbtnCount" value =' + data[i] + '>' + data[i] + '<br/>');
                    radioBtn.appendTo('#table1');
                }
//                var $this = $(this), $clone = $this.clone();
//                $this.after($clone).appendTo(hiddenform);
//                $('input.file1').val("HELLO");
//                var file = document.('file');
                
            }
        });
        return false;
    }
});

$(document).on('submit', '#UploadToDatabase', function (e) {
    e.preventDefault();
    var rbtnCounts = document.getElementsByName('rbtnCount');
    console.log("HELLO");
    var rate_value;
    var intCount = -1;
    for (var i = 0; i < rbtnCounts.length; i++) {
        if (rbtnCounts[i].checked) {
            rate_value = rbtnCounts[i].value;
            intCount = i;
            console.log(intCount);
            console.log(rbtnCounts.value);
            console.log("HELLO");
            $('input.SheetValue').val(intCount);     
        }
    }  
    if (intCount<-1) {
        alert("Choose Sheet to Upload");
        return false;
    }else{
          $("#UploadToDatabase").submit();
    }
});