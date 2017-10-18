/**
 * Created by 陈 on 2017/10/13.
 */

var projectName = "teachers";
//将js对象填充入form表单
function loadData(form,obj) {
    console.log(obj);
    for(var attr in obj ){
        // if(typeof(obj[attr] == 'function')){
        //     continue;
        // }
        console.log(attr);
        //inpupt输入框
        var $input = $("#" + form + " input[name='"+attr+"']");
        var type = $input.attr("type");
        if(type=="checkbox" ||type=="radio"){
            if (obj[attr] != null && obj[attr] != "") {
                var avalues = obj[attr].split(",");
                for(var v=0; v<avalues.length;v++){
                    $input.each(function(i,n){
                        var value = $(n).val();
                        if(value == avalues[v]){
                            $(n).attr("checked","checked");
                        }
                    });
                }
            }

        }else{
            $input.val(obj[attr]);
        }

        //textArea输入框
        var $textArea = $("#" + form + " textarea[name='"+attr+"']");
        $textArea.html(obj[attr]);
        //select
        var $select = $("#" + form + " select[name='"+attr+"'] " + " option[value='" + obj[attr] + "']");
        $select.attr("selected","selected");
    }
}
