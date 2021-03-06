/**
 * Created by 陈 on 2017/11/11.
 */

function loadArticle(){
    var saveUrl = "/teachers/article/addArticle.do";
    var deleteUrl = "/teachers/article/deleteArticle.do";
    var updateUrl = "/teachers/article/editArticle.do";
    var loadDataUrl = "/teachers/article/loadArticle.do";
    $.jgrid.defaults.styleUI = 'Bootstrap';
    var lastFlag;
    $("#tArticleList").jqGrid({
     url: loadDataUrl,
		datatype: "json",
        height: 170,
        editable: true,
        autowidth: true,
        shrinkToFit: true,
        multiSort:true,
        rowNum: 5,
        rowList: [5, 10, 15],
        colNames: ['类型','论文名称', '发表时间', '刊物名称', '刊物级别', '刊物刊号','页码','期数','作者排名（1为第一作者）','是否为通讯作者','备注','附件上传','附件下载','上传时间','操作'],
        colModel: [
            {
                name:'type',
                index:'type',
                search:true,
                width: 100,
                editable: true,
                edittype: 'select',
                editoptions:{
                    dataUrl: '',
                    buildSelect:function(responseData){
                        var selectHtml = '<select><option></option>';
                        selectHtml += '<option value="科研">' + "科研" + '</option>';
                        selectHtml += '<option value="教学">' + "教学" + '</option>';
                        selectHtml += '</select>';
                        return selectHtml;
                    }
                },
                editrules: {
                    required: true
                },
                stype: 'select',
                searchoptions: {
                    sopt: ['eq', 'ne'],
                    dataUrl: '',
                    buildSelect:function(responseData){
                        var selectHtml = '<select><option></option>';
                        selectHtml += '<option value="科研">' + "科研" + '</option>';
                        selectHtml += '<option value="教学">' + "教学" + '</option>';
                        selectHtml += '</select>';
                        return selectHtml;
                    }
                }
            },
            {
                name: 'articleName',
                index: 'articleName',
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
                name: 'postTime',
                index: 'postTime',
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
                name: 'publicationName',
                index: 'publicationName',
                search: true,
                width: 90,
                sortable: true,
                editable: true,
                editrules: {
                    required: true
                }
            },
            {
                name: 'publicationLevel',
                index: 'publicationLevel',
                width: 90,
                // stype: 'integer',
                editable: true,
                editrules: {
                    required: true,
                    // integer: true
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                    // integer: true
                }
            },
            {
                name: 'publicationId',
                index: 'publicationId',
                width: 90,
                // stype: 'integer',
                editable: true,
                editrules: {
                    required: true,
                    // integer: true
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                    // integer: true
                }
            },
            {
                name:'nums',
                index:'nums',
                width: 90,
                // stype: 'integer',
                editable: true,
                editrules: {
                    required: true,
                    // integer: true
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                    // integer: true
                }
            },
            {
                name:'periods',
                index:'periods',
                width: 90,
                // stype: 'integer',
                editable: true,
                editrules: {
                    required: true,
                    // integer: true
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                    // integer: true
                }
            },

            {
                name: 'level',
                index: 'level',
                width: 90,
                stype: 'integer',
                editable: true,
                editrules: {
                    required: true,
                    integer: true
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                    integer: true
                }
            },
            {
                name: 'isCall',
                index: 'isCall',
                width: 90,
                // stype: 'integer',
                editable: true,
                editrules: {
                    required: true,
                    // integer: true
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                    // integer: true
                }
            },
            {
                name: 'notice',
                index: 'notice',
                width: 90,
                // stype: 'integer',
                editable: true,
                // editrules: {
                //     required: true,
                //     // integer: true
                // },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true,
                    // integer: true
                }
            },
            // {
            //     name: 'status',
            //     index: 'status',
            //     width: 90,
            //     editable: false,
            //     editrules: {
            //         required: false ,
            //     },
            //     search: true,
            //     sortable: true,
            //     searchoptions: {
            //         sopt: ['eq', 'ne']
            //     },
            //     searchrules: {
            //         required: true,
            //     }
            // },
            {
                // formatter:function typeformatter(cellvalue, options, rowObject){
                //     var fileHtml="<button onclick = 'uploadFile("+rowObject+")'>上传文件</button>>";
                //     return fileHtml;
                // },
                formatter:function (cellvalue, options, row) {
                    return '<a href="#" onclick="uploadFile(' + row.id + ')">上传文件</a>&nbsp;&nbsp;';
                },
                width: 120,
                align: "left",
                editable: false,
                editrules: {
                    required: false ,
                },
            },
            {
                name: 'articleSrc',
                index: 'articleSrc',
                // formatter:function typeformatter(cellvalue, options, rowObject){
                //     var fileHtml="<button onclick = 'uploadFile("+rowObject+")'>上传文件</button>>";
                //     return fileHtml;
                // },
                formatter:function (cellvalue, options, row) {
                    return '<a href="#" onclick="downLoadFile(' + row.id + ')">' + row.fileName + '</a>&nbsp;&nbsp;';
                },
                width: 120,
                align: "left",
                editable: false,
                editrules: {
                    required: false ,
                },
            },
            {
                name: 'uploadTime',
                index: 'uploadTime',
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
                viewable: false,
                sortable: false,
                formatter : 'actions', // 在每一行显示edit按钮与delete按钮
                formatoptions : {          // 按钮设定
                    url: updateUrl,  //in-line update对应的接口
                    delOptions: {
                        url: deleteUrl,  //delete对应接口
                        afterSubmit: function (response, postdata) {
                            console.log(response)
                            var result = response.responseJSON.success;
                            return [result, 'fail to delete！', postdata.id];
                        }
                    }
                }
            }
        ],
        sortname: 'postTime',
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
        pager: "#pagerTArticleList",
        add: true,
        edit: true,
        addtext: 'Add',
        edittext: 'Edit',
        hidegrid: false
    });

    // Add selection
    $("#tArticleList").setSelection(4, true);
    // Setup buttons
    $("#tArticleList").jqGrid('navGrid', '#pagerTArticleList', {
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
            console.log(response)
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
            var result = response.responseJSON.success;
            return [result,'fail to delete！',postdata.id];
        }
    },{
        //搜素按钮对应search框设置
        multipleSearch:true,   //是否开启多条件search功能
        caption: "搜索...",  //search模态框标头
        multipleGroup: true, //复杂条件与或search
        Find: "搜索",  //search按钮显示名称
        Reset: "重置", //reset按钮名称
        // top: 100,
        showQuery: false,  //是否在search模态框中显示生成的search条件语句
        searchOnEnter: true,  //按下回车建是否开始search
        groupOps: [ { op: "AND", text: "满足所有条件" }, { op: "OR", text: "满足任一条件" } ],  //逻辑条件名称设置
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
        var width = $('.tArticle_wrapper').width();
        $('#tArticleList').setGridWidth(width);
    });
}

function loadCopyRight(){
    var saveUrl = "/teachers/copyRight/addCopyRight.do";
    var deleteUrl = "/teachers/copyRight/deleteCopyRight.do";
    var updateUrl = "/teachers/copyRight/editCopyRight.do";
    var loadDataUrl = "/teachers/copyRight/loadCopyRight.do";
    $.jgrid.defaults.styleUI = 'Bootstrap';
    var lastFlag;

    $("#tCopyRightList").jqGrid({
        url: loadDataUrl,
		datatype: "json",
        height: 170,
        editable: true,
        autowidth: true,
        shrinkToFit: true,
        multiSort:true,
        rowNum: 5,
        rowList: [5, 10, 15],
        colNames: ['作者姓名', '标题','ISSBN', '专著/教材/译著/其他','出版社','出版时间','著作号','其他参与者','备注','操作'],
        colModel: [
            {
                name: 'ownerName',
                index: 'ownerName',
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
                name: 'title',
                index: 'title',
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
                name: 'issbn',
                index: 'issbn',
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
                name: 'type',
                index: 'type',
                search: true,
                width: 150,
                sortable: true,
                editable: true,
                editrules: {
                    required: true
                },
            },
            {
                name: 'publishName',
                index: 'publishName',
                width: 90,
                editable: true,
                editrules: {
                    required: true
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true
                }
            },
            {
                name: 'publishTime',
                index: 'publishTime',
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
                name: 'publishId',
                index: 'publishId',
                width: 90,
                editable: true,
                editrules: {
                    required: true
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true
                }
            },
            {
                name: 'otherParticipator',
                index: 'otherParticipator',
                width: 90,
                editable: true,
                editrules: {
                    required: true
                },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true
                }
            },
            {
                name: 'notice',
                index: 'notice',
                width: 90,
                editable: true,
                // editrules: {
                //     required: true
                // },
                search: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne', 'lt', 'le', 'gt']
                },
                searchrules: {
                    required: true
                }
            },
            // {
            //     name: 'status',
            //     index: 'status',
            //     width: 90,
            //     editable: false,
            //     editrules: {
            //         required: false ,
            //     },
            //     search: true,
            //     sortable: true,
            //     searchoptions: {
            //         sopt: ['eq', 'ne']
            //     },
            //     searchrules: {
            //         required: true,
            //     }
            // },
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
        sortname: 'publishTime',
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
        pager: "#pagerTCopyRightList",
        add: true,
        edit: true,
        addtext: 'Add',
        edittext: 'Edit',
        hidegrid: false
    });

    // Add selection
    $("#tCopyRightList").setSelection(4, true);
    // Setup buttons
    $("#tCopyRightList").jqGrid('navGrid', '#pagerTCopyRightList', {
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
            console.log("cccccccc")
            console.log(response)
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
            var result = response.responseJSON.success;
            return [result,'fail to delete！',postdata.id];
        }
    },{
        //搜素按钮对应search框设置
        multipleSearch:true,   //是否开启多条件search功能
        caption: "搜索...",  //search模态框标头
        multipleGroup: true, //复杂条件与或search
        Find: "搜索",  //search按钮显示名称
        Reset: "重置", //reset按钮名称
        // top: 100,
        showQuery: false,  //是否在search模态框中显示生成的search条件语句
        searchOnEnter: true,  //按下回车建是否开始search
        groupOps: [ { op: "AND", text: "满足所有条件" }, { op: "OR", text: "满足任一条件" } ],  //逻辑条件名称设置
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
        var width = $('.pagerTCopyRightList').width();
        $('#tCopyRightList').setGridWidth(width);
    });
}

function uploadFile(rowid){
    console.log(rowid)
    //清空form弹窗内内容
    $("#addFormContent").html("");


    // 往form中塞入输入域
    var content = "<div class='form-group' id='enterCourse'></div>\n"+
        "                            <div class=\"form-group\">\n" +
        "                                <label class=\"col-sm-3 control-label\">选择文件：</label>\n" +
        "                                <div class=\"col-sm-8\" style=\"display:inline-flex\">\n" +
        "                                    <input id=\"fileSrc\" type=\"file\" name=\"fileSrc\" required=\"\" style=\"display: none\" onchange='tran_value()' data-rowid = '"+ rowid+"'>\n" +
        "                                    <input style=\"width: 180px;margin-right: 20px;\" id=\"fileName\" name=\"fileName\" readonly=\"true\"  aria-required=\"true\"  class=\"form-control\" required=\"\">\n" +
        "                                    <button type=\"button\" class=\"btn btn-w-m btn-default\" onclick=\"fileSrc.click()\" style=\"height: 30px;text-align: center\">上传</button>\n" +
        "                                </div>\n" +
        "                            </div>";
    $("#addFormContent").html(content);
    $("#addFormModel").modal();
}

// 设置点击保存按钮后触发的事件,此处可以写成路径地址
$("#addForm").attr("onsubmit", 'saveFile()');

function saveFile() {
    var fd = new FormData();
    var rowid = $("#fileSrc").attr("data-rowid");
    fd.append("file", $("#fileSrc").get(0).files[0]);
    fd.append("id",rowid);
    var user_id;
    //获取user_id和id
    $.ajax({
        type: 'post',
        url: '/teachers/article/getUserIdInArticle.do',
        async: false,

        xhrFields: {
            withCredentials: true
        },
        processData: false,
        contentType: false,
        // dataType: 'json',
        success:function(data){
            user_id = data.user_id
            console.log(data)
        },
        error:function(e){
            console.log(e)
        }
    })
    fd.append("user_id",user_id);
    $.ajax({
        type: 'post',
        url: 'http://192.168.213.253:8099/teachers_files/article/uploadArticle.do',
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


function downLoadFile(id){
    $.ajax({
        type: 'post',
        url: '/teachers/article/getArticleSrcById.do',
        // async: false,
        xhrFields: {
            withCredentials: true
        },
        // processData: false,
        // contentType: false,
        data: {
            'id':id
        },
        // dataType: 'json',
        success:function(data){
            console.log(data)
            window.location.href = data;
        },
        error:function(e){
            console.log(e)
        }
    })

}

function tran_value() {

    $("#fileName").val($("#fileSrc").val());
}

function downLoadArticleTemp() {
    window.location.href = "/teachers/fileTemplates/article.xlsx";
}

function inArticle() {
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
    $("#addForm").attr("onsubmit", 'inArticleTemp()');
}

function inArticleTemp() {
    var fd = new FormData();
    var rowid = $("#fileSrc").attr("data-rowid");
    fd.append("file", $("#fileSrc").get(0).files[0]);

    $.ajax({
        type: 'post',
        url: '/teachers/article/inArticle.do',
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


function downLoadCopyRightTemp() {
    window.location.href = "/teachers/fileTemplates/copyRight.xlsx";
}

function inCopyRight() {
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
    $("#addForm").attr("onsubmit", 'inCopyRightTemp()');
}

function inCopyRightTemp() {
    var fd = new FormData();
    var rowid = $("#fileSrc").attr("data-rowid");
    fd.append("file", $("#fileSrc").get(0).files[0]);

    $.ajax({
        type: 'post',
        url: '/teachers/copyRight/inCopyRight.do',
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
    loadArticle();
    loadCopyRight();
    // alert($("td [aria-describedby='tArticleList_article_src']").val());
})
