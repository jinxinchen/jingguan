//加载基本信息
function loadBaseInfo() {
    $.ajax({
        type:"GET",
        url:"/teachers/baseInfo/getBaseInfo.do",
        async:true,
        success:function (e) {
            console.log(e);
            if(e[0] != null){
                $("#baseInfoForm input[name='name']").val(e[0].name);
                $("#baseInfoForm input[name='birthday']").val(e[0].birthday );
                $("#baseInfoForm input[name='identityNum']").val(e[0].identityNum );
                $("#baseInfoForm input[name='email']").val(e[0].email );
                $("#baseInfoForm input[name='phoneNum']").val(e[0].phoneNum );
                $("#baseInfoForm input[name='address']").val(e[0].address );

                $("#personalPic").attr('src',e[0].picture);
                $("#identityPic").attr('src',e[0].identityPic);
                if(e[0].gender == "男"){
                    $("#gender1").attr("checked","checked");
                }else if(e[0].gender == "女"){
                    $("#gender2").attr("checked","checked");
                }

                if(e[0].isMoreLangue == 1){
                    $("#yesmorelanguage").attr("checked","checked");
                }else if(e[0].isMoreLangue == 0){
                    $("#nomorelanguage").attr("checked","checked");
                }

                $("#degree").val(e[0].degree);
                $("#degreeType").val(e[0].degreeType);

                $("#baseInfoForm").attr("onsubmit","updateBaseInfo()");
                $("#baseInfoBt").html("更新");
            }else{
                $("#baseInfoForm").attr("onsubmit", "saveBaseInfo()");
                $("#baseInfoBt").html("保存");
            }



        }
    })
}

function updateBaseInfo(){
    var updateUrl = "/teachers/baseInfo/updateBaseInfo.do"
    var formData = new FormData(document.getElementById("baseInfoForm"));
    console.log(formData)
    $.ajax({
        type:'post',
        url: updateUrl,
        async: false,
        processData: false,
        contentType: false,
        data: formData,
        // datatype:json,
        success:function(res){
            console.log(234)
            console.log(res)
        },
        error:function(e){
            console.log(e)
        }
    })
}

function saveBaseInfo(){
    var saveUrl = "/teachers/baseInfo/saveBaseInfo.do";
    var formdata = new FormData(document.getElementById("baseInfoForm"));
    $.ajax({
        type:'post',
        url: saveUrl,
        async: false,
        xhrFields: {
            withCredentials: true
        },
        processData: false,
        contentType: false,
        data: formdata,
        success:function(res){
            console.log(1234)
            console.log(res)
        },
        error:function(e){
            console.log(e)
        }
    })
}




function loadEduExp(){
	var saveUrl = "/"+projectName+"/education/addEduExp.do";
    var deleteUrl = "/"+projectName+"/education/deleteEduExp.do";
    var updateUrl = "/"+projectName+"/education/editEduExp.do";
    var loadDataUrl = "/"+projectName+"/education/loadEduExp.do";
	$.jgrid.defaults.styleUI = 'Bootstrap';
    var lastFlag;
    // var testData = [{
    //         school: "华侨大学",
    //         major: "软件工程",
    //         education: "本科生",
    //         graduationYear: "2019-06-30"
    //     }, {
    //     	school: "华侨大学",
    //         major: "软件工程",
    //         education: "本科生",
    //         graduationYear: "2019-06-30"
    //     }];
    $("#tEduExList").jqGrid({
        url: loadDataUrl,
		datatype: "json",
		// data:testData,
        // datatype: "local",
        mtype: 'GET',
        height: 170,
        editable: true,
        autowidth: true,
        shrinkToFit: true,
        multiSort:true,
        rowNum: 5,
        rowList: [5, 10, 15],
        colNames: ['学校', '专业', '教育背景', '入学年份','毕业年份', '操作'],
        colModel: [
            {
                name: 'school',
                index: 'school',
                search: true,
                editable: true,
                editrules: {
                    required: true
                },
                width: 150,
                sortable: false,
                searchoptions: {
                    sopt: ['eq', 'ne','cn','nc']
                },
                searchrules: {
                    // required: true
                }
            },
            {
                name: 'major',
                index: 'major',
                editable: true,
                editrules: {
                    required: true
                },
                search: true,
                width: 150,
                sortable: false,
                searchoptions: {
                    sopt: ['eq', 'ne','cn','nc']
                }
            },
            {
                name: 'education',
                index: 'education',
                search: true,
                width: 150,
                sortable: false,
                editable: true,
                searchoptions: {
                    sopt: ['eq', 'ne','cn','nc']
                },
                editrules: {
                    required: true
                },
            },
            {
                name: 'entrance',
                index: 'entrance',
                editable:true,width:200,sorttype:"date",formatter:"date",
                editrules: {
                    required: true
                },
                editoptions: {
                    dataInit: function (element) {
                        $(element).attr("readonly", "readonly");
                        $(element).on("click", function () {
                            laydate({istime: false, format: 'YYYY-MM-DD', choose: function(dates){ //选择好日期的回调
                                $(element).trigger("change");
                            }})
                        })
                    }
                },
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt'],
                    dataInit: function (element) {
                        $(element).attr("readonly", "readonly");
                        $(element).on("click", function () {
                            laydate({istime: false, format: 'YYYY-MM-DD', choose: function(dates){ //选择好日期的回调
                                $(element).trigger("change");
                            }})
                        })
                    }
                }
            },
            {
                name: 'graduationYear',
                index: 'graduationYear',
                editable:true,width:200,sorttype:"date",formatter:"date",
                editrules: {
                    required: true
                },
                editoptions: {
                    dataInit: function (element) {
                        $(element).attr("readonly", "readonly");
                        $(element).on("click", function () {
                            laydate({istime: false, format: 'YYYY-MM-DD', choose: function(dates){ //选择好日期的回调
                                $(element).trigger("change");
                            }})
                        })
                    }
                },
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt'],
                    dataInit: function (element) {
                        $(element).attr("readonly", "readonly");
                        $(element).on("click", function () {
                            laydate({istime: false, format: 'YYYY-MM-DD', choose: function(dates){ //选择好日期的回调
                                $(element).trigger("change");
                            }})
                        })
                    }
                }
            },
            {
                viewable: false,
                sortable: false,
                formatter : 'actions', // 在每一行显示edit按钮与delete按钮
                formatoptions : {          // 按钮设定
                    url: updateUrl,  //in-line update对应的接口
                    delOptions: {
                        url: deleteUrl,  //delete对应接口
                        afterSubmit: function (response, postdata) {
                            var result = response.responseJSON.success;
                            return [result, 'fail to delete！', postdata.id];
                        }
                    }
                }
            }
        ],
        sortname: 'graduationYear',
        sortorder: "desc",
        prmNames: {
            rows: 'limit',
            page: 'page'
        },
        jsonReader: {
            root: 'result',
            total: 'pages',
            page: 'page',
            records: 'recores',
            repeatitems: false
        },
        viewrecords: true,  //是否显示总记录数
        rownumbers: true,  //是否显示行号
        multiselect: true,  //是否有多选功能

        // caption: "学生记录",
        pager: "#pagerTEduExList",
        add: true,
        edit: true,
        addtext: 'Add',
        edittext: 'Edit',
        hidegrid: false
    });
    
    // Add selection
    $("#tEduExList").setSelection(4, true);
    // Setup buttons
    $("#tEduExList").jqGrid('navGrid', '#pagerTEduExList', {
        edit: true,
        add: true,
        del: true,
        search: true,
        view: true  //是否可以以模态框显示记录
    }, {//edit按钮选项
        key: true,
        url: updateUrl,
        mtype: 'POST',
        editCaption: "edit",
        restoreAfterError: true,
        afterSubmit : function(response, postdata) {
            var result = response.responseJSON.success;
            return [result,'fail to update！',postdata.id];
        },
        closeAfterEdit: true,
        extraparam: {
        }
    },{
        //添加按钮选项
        url: saveUrl,
        datatype: 'json',
        serializeEditData: function(postData) {
            postData.id = "";
            return postData;
        },
        closeAfterAdd: true,
        afterSubmit : function(response, postdata) {
            var result = response.responseJSON.success;
            return [result,'save failed！',postdata.id];
        }
    },{
        //delete按钮选项
        url: deleteUrl,  //delete对应接口
        afterSubmit : function(response, postdata) {
            console.log(response)
            var result = response.responseJSON.success;
            return [result,'fail to delete！',postdata.id];
        }
    },{
        //搜素按钮对应search框设置
        multipleSearch:true,   //是否开启多条件search功能
        caption: "search...",  //search模态框标头
        multipleGroup: true, //复杂条件与或search
        Find: "search",  //search按钮显示名称
        Reset: "reset", //reset按钮名称
        // top: 100,
        showQuery: false,  //是否在search模态框中显示生成的search条件语句
        searchOnEnter: true,  //按下回车建是否开始search
        groupOps: [ { op: "AND", text: "Satisfy all the conditions" }, { op: "OR", text: "Satisfy any conditions" } ],  //逻辑条件名称设置
        // jqModal: true,
        // modal: true,
        drag: true, //search模态框是否能够被拖拽动
    },{
        //view按钮对应选项
        // height: 200,
        drag: false, //search模态框是否能够被拖拽动
        reloadAfterSubmit: true,
        closeOnEscape:true  //按钮ESC键，弹窗消失
    });
    // Add responsive to jqGrid
    $(window).bind('resize', function () {
        var width = $('.tEduEx_wrapper').width();
        $('#tEduExList').setGridWidth(width);
    });

}

function loadWorkExp() {
	var saveUrl = "/"+projectName+"/work/addWorkExp.do";
    var deleteUrl = "/"+projectName+"/work/deleteWorkExp.do";
    var updateUrl = "/"+projectName+"/work/editWorkExp.do";
    var loadDataUrl = "/"+projectName+"/work/loadWorkExp.do";
	$.jgrid.defaults.styleUI = 'Bootstrap';
    var lastFlag;
    $("#tWorkExList").jqGrid({
        url: loadDataUrl,
		datatype: "json",
		// data:testData,
        // datatype: "local",
        height: 170,
        editable: true,
        autowidth: true,
        shrinkToFit: true,
        multiSort:true,
        rowNum: 4,
        rowList: [5, 10, 15],
        colNames: ['学校', '职称', '工作部门', '工作岗位', '工作内容', '开始时间','结束时间','操作'],
        colModel: [
            {
                name: 'organization',
                index: 'organization',
                search: true,
                editable: true,
                editrules: {
                    required: true
                },
                width: 150,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne','cn','nc']
                },
                searchrules: {
                    // required: true
                }
            },
            {
                name:'professionalTitle',
                index:'professionalTitle',
                search: true,
                editable: true,
                editrules: {
                    required: true
                },
                width: 150,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne','cn','nc']
                },
                searchrules: {
                    // required: true
                }
            },
            {
                name: 'department',
                index: 'department',
                editable: true,
                editrules: {
                    required: true
                },
                search: true,
                width: 150,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'post',
                index: 'post',
                search: true,
                width: 150,
                sortable: true,
                editable: true,
                // edittype: 'select',
//              editoptions: {
////                  dataUrl: '/' + project_name + '/teacher/education/listNames.do',
//                  buildSelect: function (responseData) {
////                      var response = $.parseJSON(responseData);
////                      var data = response.data;
//						var data =['本科','硕士','博士','博士后']; 
//                      var selectHtml = '<select><option></option>';
//                      for (var i = 0; i < data.length; i++) {
//                          var dataValue = data[i];
//                          selectHtml += ('<option value="' + dataValue + '">' + dataValue + '</option>');
//                      }
//                      selectHtml += '</select>';
//                      return selectHtml
//                  }
//              },
                editrules: {
                    required: true
                },
                // stype: 'select',
//              searchoptions: {
//                  sopt: ['eq', 'ne'],
////                  dataUrl: '/' + project_name + '/teacher/education/listNames.do',
////                  buildSelect: function (responseData) {
////                      var response = $.parseJSON(responseData);
////                      var data = response.data;
//						var data =['本科','硕士','博士','博士后']; 
//                      var selectHtml = '<select><option></option>';
//                      for (var i = 0; i < data.length; i++) {
//                          var dataValue = data[i];
//                          selectHtml += ('<option value="' + dataValue + '">' + dataValue + '</option>');
//                      }
//                      selectHtml += '</select>';
//                      return selectHtml
////                  }
//              }
            },
            {
                name: 'content',
                index: 'content',
                width: 90,
                // stype: 'integer',
                editable: true,
                editrules: {
                    required: true,
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                }
            },
            {
                name: 'startTime',
                index: 'startTime',
                width: 90,
                stype: 'integer',
                editable: true,
                editrules: {
                    required: true,
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                }
            },
            {
                name: 'endTime',
                index: 'endTime',
                width: 90,
                stype: 'integer',
                editable: true,
                editrules: {
                    required: true,
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                }
            },
            {
                viewable: false,
                sortable: false,
                formatter : 'actions', // 在每一行显示edit按钮与delete按钮
                formatoptions : {          // 按钮设定
                    url: updateUrl,  //in-line update对应的接口
                    delOptions: {
                        url: deleteUrl,  //delete对应接口
                        afterSubmit: function (response, postdata) {
                            var result = response.responseJSON.success;
                            return [result, 'fail to delete！', postdata.id];
                        }
                    }
                }
            }
        ],
        sortname: 'endTime',
        sortorder: "desc",
        prmNames: {
            rows: 'limit',
            page: 'page'
        },
        jsonReader: {
            root: 'result',
            total: 'pages',
            page: 'page',
            records: 'recores',
            repeatitems: false
        },
        viewrecords: true,  //是否显示总记录数
        rownumbers: true,  //是否显示行号
        multiselect: true,  //是否有多选功能

        // caption: "学生记录",
        pager: "#pagerTWorkExList",
        add: true,
        edit: true,
        addtext: 'Add',
        edittext: 'Edit',
        hidegrid: false
    });
    
    // Add selection
    $("#tWorkExList").setSelection(4, true);
    // Setup buttons
    $("#tWorkExList").jqGrid('navGrid', '#pagerTWorkExList', {
        edit: true,
        add: true,
        del: true,
        search: true,
        view: true  //是否可以以模态框显示记录
    }, {//edit按钮选项
        key: true,
        url: updateUrl,
        mtype: 'POST',
        editCaption: "edit",
        restoreAfterError: true,
        afterSubmit : function(response, postdata) {
            var result = response.responseJSON.success;
            return [result,'fail to update！',postdata.id];
        },
        closeAfterEdit: true,
        extraparam: {
        }
    },{
        //添加按钮选项
        url: saveUrl,
        datatype: 'json',
        serializeEditData: function(postData) {
            postData.id = "";
            return postData;
        },
        closeAfterAdd: true,
        afterSubmit : function(response, postdata) {
            var result = 'success';
            return [result,'save failed！',postdata.id];
        }
    },{
        //delete按钮选项
        url: deleteUrl,  //delete对应接口
        afterSubmit : function(response, postdata) {
            var result = response.responseJSON.success;
            return [result,'fail to delete！',postdata.id];
        }
    },{
        //搜素按钮对应search框设置
        multipleSearch:true,   //是否开启多条件search功能
        caption: "search...",  //search模态框标头
        multipleGroup: true, //复杂条件与或search
        Find: "search",  //search按钮显示名称
        Reset: "reset", //reset按钮名称
        // top: 100,
        showQuery: false,  //是否在search模态框中显示生成的search条件语句
        searchOnEnter: true,  //按下回车建是否开始search
        groupOps: [ { op: "AND", text: "Satisfy all the conditions" }, { op: "OR", text: "Satisfy any conditions" } ],  //逻辑条件名称设置
        // jqModal: true,
        // modal: true,
        drag: true, //search模态框是否能够被拖拽动
    },{
        //view按钮对应选项
        // height: 200,
        drag: false, //search模态框是否能够被拖拽动
        reloadAfterSubmit: true,
        closeOnEscape:true  //按钮ESC键，弹窗消失
    });
    // Add responsive to jqGrid
    $(window).bind('resize', function () {
        var width = $('.tWorkEx_wrapper').width();
        $('#tWorkExList').setGridWidth(width);
    });
}


function outExcel() {
    tableExport('tEduExList', '1', 'xls');

}


function uploadFile(){
    //清空form弹窗内内容
    $("#addFormContent").html("");


    // 往form中塞入输入域
    var content = "<div class='form-group' id='enterCourse'></div>\n"+
        "                            <div class=\"form-group\">\n" +
        "                                <label class=\"col-sm-3 control-label\">上传照片：</label>\n" +
        "                                <div class=\"col-sm-8\" style=\"display:inline-flex\">\n" +
        "                                    <input id=\"fileSrc\" type=\"file\" name=\"fileSrc\" required=\"\" style=\"display: none\" onchange='tran_value()'>\n" +
        "                                    <input style=\"width: 180px;margin-right: 20px;\" id=\"fileName\" name=\"fileName\" readonly=\"true\"  aria-required=\"true\"  class=\"form-control\" required=\"\">\n" +
        "                                    <button type=\"button\" class=\"btn btn-w-m btn-default\" onclick=\"fileSrc.click()\" style=\"height: 30px;text-align: center\">上传</button>\n" +
        "                                </div>\n" +
        "                            </div>";
    $("#addFormContent").html(content);
    $("#addFormModel").modal();


// 设置点击保存按钮后触发的事件,此处可以写成路径地址
    $("#addForm").attr("onsubmit", 'saveFile()');
}

function uploadIdentityFile(){
    //清空form弹窗内内容
    $("#addFormContent").html("");


    // 往form中塞入输入域
    var content = "<div class='form-group' id='enterCourse'></div>\n"+
        "                            <div class=\"form-group\">\n" +
        "                                <label class=\"col-sm-3 control-label\">上传照片：</label>\n" +
        "                                <div class=\"col-sm-8\" style=\"display:inline-flex\">\n" +
        "                                    <input id=\"fileSrc\" type=\"file\" name=\"fileSrc\" required=\"\" style=\"display: none\" onchange='tran_value()'>\n" +
        "                                    <input style=\"width: 180px;margin-right: 20px;\" id=\"fileName\" name=\"fileName\" readonly=\"true\"  aria-required=\"true\"  class=\"form-control\" required=\"\">\n" +
        "                                    <button type=\"button\" class=\"btn btn-w-m btn-default\" onclick=\"fileSrc.click()\" style=\"height: 30px;text-align: center\">上传</button>\n" +
        "                                </div>\n" +
        "                            </div>";
    $("#addFormContent").html(content);
    $("#addFormModel").modal();

    // 设置点击保存按钮后触发的事件,此处可以写成路径地址
    $("#addForm").attr("onsubmit", 'saveIdentityFile()');
}




function saveFile() {
    var fd = new FormData();
    fd.append("file", $("#fileSrc").get(0).files[0]);
    var user_id;
    var id;
    //获取user_id和id
    $.ajax({
        type: 'post',
        url: '/teachers/baseInfo/getUserIdAndId.do',
        async: false,

        xhrFields: {
            withCredentials: true
        },
        processData: false,
        contentType: false,
        // dataType: 'json',
        success:function(data){
            id = data.id;
            user_id = data.user_id
        },
        error:function(e){
            console.log(e)
        }
    })
    fd.append("user_id",user_id);
    fd.append("id",id);
    $.ajax({
        type: 'post',
        url: 'http://192.168.213.253:8099/teachers_files/baseInfoFile/uploadPic.do',
        async: false,
        data: fd,
        xhrFields: {
            withCredentials: true
        },
        processData: false,
        contentType: false,
        // dataType: 'json',
        success:function(data){
            console.log(data)
            console.log(data.id)
        },
        error:function(e){
            console.log(e)
        }
    })



}

function saveIdentityFile() {
    var fd = new FormData();
    fd.append("file", $("#fileSrc").get(0).files[0]);

    var user_id;
    var id;
    //获取user_id和id
    $.ajax({
        type: 'post',
        url: '/teachers/baseInfo/getUserIdAndId.do',
        async: false,

        xhrFields: {
            withCredentials: true
        },
        processData: false,
        contentType: false,
        // dataType: 'json',
        success:function(data){
            id = data.id;
            user_id = data.user_id
        },
        error:function(e){
            console.log(e)
        }
    })
    fd.append("user_id",user_id);
    fd.append("id",id);

    $.ajax({
        type: 'post',
        url: 'http://192.168.213.253:8099/teachers_files/baseInfoFile/uploadIdentityPic.do',
        async: false,
        data: fd,
        xhrFields: {
            withCredentials: true
        },
        processData: false,
        contentType: false,
        // dataType: 'json',
        success:function(data){
            console.log(data)
        },
        error:function(e){
            console.log(e)
        }
    })
}



function tran_value() {

    $("#fileName").val($("#fileSrc").val());
}

function downLoadEduTemp() {
    window.location.href = "/teachers/fileTemplates/EduExp.xlsx";
}

function inEdu() {
//清空form弹窗内内容
    $("#addFormContent").html("");


    // 往form中塞入输入域
    var content = "<div class='form-group' id='enterCourse'></div>\n"+
        "                            <div class=\"form-group\">\n" +
        "                                <label class=\"col-sm-3 control-label\">选择文件：</label>\n" +
        "                                <div class=\"col-sm-8\" style=\"display:inline-flex\">\n" +
        "                                    <input id=\"fileSrc\" type=\"file\" name=\"fileSrc\" required=\"\" style=\"display: none\" onchange='tran_value()'>\n" +
        "                                    <input style=\"width: 180px;margin-right: 20px;\" id=\"fileName\" name=\"fileName\" readonly=\"true\"  aria-required=\"true\"  class=\"form-control\" required=\"\">\n" +
        "                                    <button type=\"button\" class=\"btn btn-w-m btn-default\" onclick=\"fileSrc.click()\" style=\"height: 30px;text-align: center\">上传</button>\n" +
        "                                </div>\n" +
        "                            </div>";
    $("#addFormContent").html(content);
    $("#addFormModel").modal();
    // 设置点击保存按钮后触发的事件,此处可以写成路径地址
    $("#addForm").attr("onsubmit", 'inEduTemp()');
}

function inEduTemp() {
    var fd = new FormData();
    var rowid = $("#fileSrc").attr("data-rowid");
    fd.append("file", $("#fileSrc").get(0).files[0]);

    $.ajax({
        type: 'post',
        url: '/teachers/education/inEdu.do',
        async: false,
        xhrFields: {
            withCredentials: true
        },
        processData: false,
        contentType: false,
        data: fd,
        // dataType: 'json',
        success:function(data){
            console.log(data)
        },
        error:function(e){
            console.log(e)
        }
    })
}

function downLoadWorkTemp() {
    window.location.href = "/teachers/fileTemplates/WorkExp.xlsx";
}

function inWork() {
//清空form弹窗内内容
    $("#addFormContent").html("");


    // 往form中塞入输入域
    var content = "<div class='form-group' id='enterCourse'></div>\n"+
        "                            <div class=\"form-group\">\n" +
        "                                <label class=\"col-sm-3 control-label\">选择文件：</label>\n" +
        "                                <div class=\"col-sm-8\" style=\"display:inline-flex\">\n" +
        "                                    <input id=\"fileSrc\" type=\"file\" name=\"fileSrc\" required=\"\" style=\"display: none\" onchange='tran_value()'>\n" +
        "                                    <input style=\"width: 180px;margin-right: 20px;\" id=\"fileName\" name=\"fileName\" readonly=\"true\"  aria-required=\"true\"  class=\"form-control\" required=\"\">\n" +
        "                                    <button type=\"button\" class=\"btn btn-w-m btn-default\" onclick=\"fileSrc.click()\" style=\"height: 30px;text-align: center\">上传</button>\n" +
        "                                </div>\n" +
        "                            </div>";
    $("#addFormContent").html(content);
    $("#addFormModel").modal();
    // 设置点击保存按钮后触发的事件,此处可以写成路径地址
    $("#addForm").attr("onsubmit", 'inworkTemp()');
}

function inworkTemp() {
    var fd = new FormData();
    var rowid = $("#fileSrc").attr("data-rowid");
    fd.append("file", $("#fileSrc").get(0).files[0]);

    $.ajax({
        type: 'post',
        url: '/teachers/work/inWork.do',
        async: false,
        xhrFields: {
            withCredentials: true
        },
        processData: false,
        contentType: false,
        data: fd,
        // dataType: 'json',
        success:function(data){
            console.log(data)
        },
        error:function(e){
            console.log(e)
        }
    })
}

$(function(){
    loadBaseInfo();
	loadEduExp();
	loadWorkExp();
})
