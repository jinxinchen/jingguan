/**
 * Created by Administrator on 2017/11/13 0013.
 */




var uploadSever='http://192.168.213.253:8099/teachers_files/'

function loadActivity(){
    var saveUrl = "/teachers/teacher/teacherActivity/saveActivity.do";
    var deleteUrl = "/teachers/teacher/teacherActivity/deleteActivity.do";
    var updateUrl = "/teachers/teacher/teacherActivity/updateActivity.do";
    var loadDataUrl =  "/teachers/teacher/teacherActivity/listActivity.do";

    $.jgrid.defaults.styleUI = 'Bootstrap';


    $("#tActivity").jqGrid({
        url: loadDataUrl,
        datatype: "json",
        //  data:testData,
        //  datatype: "local",
        height: 170,
        editable: true,
        autowidth: true,
        shrinkToFit: true,

        rowNum: 5,
        rowList: [5, 10, 15],
        colNames: [
            '记录id',
            '活动名称',
            '活动地点',
            '活动时间',
            '举办单位',
            '证书附件',
            '证书下载',
            '附件上传时间',
            '备注',
            '操作'],

        //具体的每列的设计
        colModel: [
            {
                name: 'id',
                index: 'id',
                search: true,
                width: 150,
                sortable: true,
                editable: false,
                hidden: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },


            {

                name:'activityName',
                index:'activityName',
                width:150,
                editable: true,
                edittype: 'select',
                editoptions: {
                    dataUrl: '',
                    buildSelect: function (responseData) {
                        var selectHtml = '<select><option></option>';
                        selectHtml += '<option value="访学">' + "访学" + '</option>';
                        selectHtml += '<option value="培训">' + "培训" + '</option>';
                        selectHtml += '<option value="参加学术会议">' + "参加学术会议" + '</option>';
                        selectHtml += '</select>';
                        return selectHtml;
                    }
                },
            },
            {
                name:'activityLocation',
                index:'activityLocation',
                width:150,
                editable: true,
                sortable: false,
                editrules: {
                    required: true
                },
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },

            {
                name:'activityTime',
                index:'activityTime',
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
                name:'activityOrganizers',
                index:'activityOrganizers',
                width:150,
                editable: true,
                sortable: true,
                editrules: {
                    required: true
                },
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                width: 120,
                align: "left",
                editable: false,
                editrules: {
                    required: false ,
                },
                formatter:function (cellvalue, options, row) {
                    return '<a href="#" onclick="uploadFile(' + row.id + ')">上传文件</a>&nbsp;&nbsp;';
                },
            },
            {
                name:'certificate',
                index:'certificate',
                width: 120,
                align: "left",
                editable: false,
                editrules: {
                    required: false ,
                },
                formatter:function (cellvalue, options, row) {
                    var s=row.certificate;
                    if(s!=null) {
                        var index = s.lastIndexOf("\/");
                        s = s.substring(index + 1, s.length);
                    }

                    return '<a href="#" onclick="downLoadFile(' + row.id + ')">'+s+'</a>&nbsp;&nbsp;';
                },
            },
            {
                name:'certificateUploadTime',
                index:'certificateUploadTime',
                width:150,
                width: 120,
                editable: false,
                editrules: {
                    required: false ,
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                },
                searchrules: {
                    required: true,
                }
            },

            {
                name:'others',
                index:'others',
                width:150,
                editable: true,
                sortable: false,
                // editrules: {
                //     required: true
                // },
            },

            {
                viewable: false,
                sortable: false,
                formatter : 'actions', // 在每一行显示编辑按钮与删除按钮
                search: false,
                formatoptions : {          // 按钮设定
                    url: updateUrl,  //in-line 更新对应的接口
                    delOptions: {
                        url: deleteUrl,  //删除对应接口
                        afterSubmit: function (response, postdata) {
                            var result = response.responseJSON.success;
                            return [result, 'fail to delete！', postdata.id];
                        }
                    }
                }
            }
        ],

        sortname: 'activityTime',
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
        pager:'#pagerTActivity',
        add: true,
        edit: true,
        addtext: 'Add',
        edittext: 'Edit',
        hidegrid: false
    });

    // Add selection
    $("#tActivity").setSelection(4, true);
    // Setup buttons
    $("#tActivity").jqGrid('navGrid', '#pagerTActivity', {
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
        } ,
        {
            //添加按钮选项
            url: saveUrl,
            // datatype: 'json',
            serializeEditData: function(postData) {
                postData.id = "";
                return postData;
            },
            closeAfterAdd: true,
            afterSubmit : function(response, postdata) {
                var result = response.responseJSON.success;
                return [result,'save failed！',postdata.id];
            }
        },
        {
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
        var width = $('.tActivity_wrapper').width();
        $('#tActivity').setGridWidth(width);
    });

}


var recordid;
function uploadFile(rowid){

    recordid=rowid;
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
    console.log(rowid)
}

// 设置点击保存按钮后触发的事件,此处可以写成路径地址
$("#addForm").attr("onsubmit", 'saveFile()');

function saveFile() {
    //console.log("save");
    var fd = new FormData();
    fd.append("file", $("#fileSrc").get(0).files[0]);
    fd.append("id",recordid);

    $.ajax({
        type: 'post',
        //'/teachers/teacher/teacherActivity/uploadEvidence.do'
        url: uploadSever+'uploadActivity/uploadEn.do',
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

function tran_value() {

    $("#fileName").val($("#fileSrc").val());
}

function downLoadFile(rowid){
    $.ajax({
        type: 'post',
        url: '/teachers/teacher/teacherActivity/downloadEvidence.do',
        // async: false,
        xhrFields: {
            withCredentials: true
        },
        // processData: false,
        // contentType: false,
        data: {
            'id':rowid
        },
        success:function(data){
            console.log(data)
            window.location.href = data;
        },
        error:function(e){
            console.log(e)
        }
    })
    return false;
}


function downLoadActivityTemp() {
    window.location.href = "/teachers/fileTemplates/activity.xlsx";
}

function inActivity() {
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
    $("#addForm").attr("onsubmit", 'inActivityTemp()');
}

function inActivityTemp() {
    var fd = new FormData();
    var rowid = $("#fileSrc").attr("data-rowid");
    fd.append("file", $("#fileSrc").get(0).files[0]);

    $.ajax({
        type: 'post',
        url: '/teachers/teacher/teacherActivity/inActivity.do',
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
    loadActivity();
})