/**
 * Created by 陈 on 2017/11/16.
 */

//加载教师
function  loadTeachers() {
    var loadDataUrl = "/teachers/manageTeachers/loadTeachers.do";
    var updateUrl="";
    var deleteUrl="";
    var saveUrl="";
    $.jgrid.defaults.styleUI = 'Bootstrap';
    $("#tBaseInfoList").jqGrid({
        url: loadDataUrl,
        datatype: "json",
        editurl: 'remote',
        height: 500,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        // editurl: 'clientArray',
        emptyrecords : "no record",
        viewrecords: true,
        rownumbers: true,  //是否显示行号
        multiselect: false,  //是否有多选功能
        multiSort: true,
        rowList: [10, 20, 30],
        colNames: ['姓名', '性别', '联系电话','出生年月', '学位','学位类型','职称','职称等级','是否为导师','导师类型','所属系','任教专业','详情'],
        colModel: [
            {
                name: 'name',
                index: 'name',
                search: true,
                width: 150,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'gender',
                index: 'gender',
                search: true,
                width: 90,
                editable: true,
                sortable: true,
                stype: 'select',
                searchoptions: {
                    sopt: ['eq', 'ne'],
                    // value: "man:man;woman:woman"
                }
            },
            {
                name: 'phoneNum',
                index: 'phoneNum',
                search: true,
                width: 150,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'birthday',
                index: 'birthday',
                editable:true,width:180,sorttype:"date",formatter:"date",
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
                name: 'degree',
                index: 'degree',
                search: true,
                width: 120,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'degreeType',
                index: 'degreeType',
                search: true,
                width: 180,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'educateDegreeName',
                index: 'educateDegreeName',
                search: true,
                width: 150,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'educateDegreeLevel',
                index: 'educateDegreeLevel',
                search: true,
                width: 180,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'isMentor',
                index: 'isMentor',
                search: true,
                width: 200,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'mentorType',
                index: 'mentorType',
                search: true,
                width: 180,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'major',
                index: 'major',
                search: true,
                width: 150,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                name: 'forClass',
                index: 'forClass',
                search: true,
                width: 180,
                editable: true,
                sortable: true,
                searchoptions: {
                    sopt: ['eq', 'ne']
                }
            },
            {
                width: 150,
                align: "center",
                search:false,
                sortable: false,
                formatter: function (cellvalue, options, row) {
                    return '<a href="#" onclick="showDetail(\'' + row.userId + '\')">查看详情</a>&nbsp;&nbsp;';
                }
            }
        ],
        pager: "#pagerTBaseInfoList",
        sortname: 'name',
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

        pager: "#pagerTBaseInfoList",
        add: false,
        edit: true,
        addtext: 'Add',
        edittext: 'Edit',
        hidegrid: false

    });


    // Add selection
    $("#tBaseInfoList").setSelection(4, true);

    $("#tBaseInfoList").jqGrid('navGrid', '#pagerTBaseInfoList', {
        edit: true,
        add: false,
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
        var width = $('.tBaseInfo_wrapper').width();
        $('#tBaseInfoList').setGridWidth(width);
    });

}

function showDetail(id) {
    // alert(id)
    window.location.href = 'baseInfo.html?id=' + id;
}

//初始化页面
$(function () {
    loadTeachers();
})
