Ext.define('FQServiceApp.view.TaskGridPanel', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.taskgridpanel',

    requires: [
        'Ext.grid.View',
        'Ext.grid.column.Column',
        'Ext.button.Button',
        'Ext.toolbar.Paging'
    ],

    id: 'taskGrid',
    title: '调度列表',
    forceFit: true,
    store: 'TaskEntityStore',

    initComponent: function() {
        var me = this;
        me.on("render",function(cmp){
            cmp.getStore().load();
        });
        Ext.applyIf(me, {
            columns: [
                {
                    xtype: 'gridcolumn',
                    sortable:false,
                    dataIndex: 'name',
                    text: '名称'
                },
                {
                    xtype: 'gridcolumn',
                    sortable:false,
                    dataIndex: 'author',
                    text: '作者'
                },
                {
                    xtype: 'gridcolumn',
                    sortable:false,
                    dataIndex: 'type',
                    text: '调度方式'
                },

                {
                    xtype: 'gridcolumn',
                    sortable:false,
                    dataIndex: 'app',
                    text: '所属平台'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value !== null) {
                            return Ext.util.Format.date(new Date(parseInt(value)),"Y-m-d H:i:s");
                        }
                    },
                    dataIndex: 'preExecTime',
                    text: '上次执行时间'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        if (value !== null) {
                            return Ext.util.Format.date(new Date(parseInt(value)),"Y-m-d H:i:s");
                        }
                    },
                    dataIndex: 'nextExecTime',
                    text: '下次执行时间'
                },
                {
                    xtype: 'gridcolumn',
                    sortable:false,
                    dataIndex: 'statusDescription',
                    text: '触发状态'
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'button',
                            text: '新建',
                            listeners: {
                                click: {
                                    fn: me.onButtonClick,
                                    scope: me
                                }
                            }
                        },
                        {
                            xtype: 'button',
                            handler: function(button, e) {
                                var me=this;
                                Ext.MessageBox.confirm('提示', '您确定要启动该任务', function(id){
                                if(id=='yes'){
                                var grid=me.up("grid");
                                var selectionList=grid.getSelectionModel().getSelection();
                                var selection = selectionList[0];
                                var raw= selection.getData();
                                Ext.Ajax.request({
                                    url: '/task/startup.json',
                                    params: {
                                        id: raw.id
                                    },
                                    success: function(response){
                                        var _response = Ext.JSON.decode(response.responseText);
                                        Ext.MessageBox.alert(Ext.ENV.MSG.title,_response.msg,function(){
                                            grid.getStore().reload();
                                            grid.getSelectionModel().clearSelections();
                                            grid.fireEvent("selectionchange",null,grid.getSelectionModel());
                                        });
                                    }
                                });
                                }
                                });
                            },
                            disabled: true,
                            id: 'start',
                            text: '启动'
                        },
                        {
                            xtype: 'button',
                            handler: function(button, e) {
                                var me=this;
                                Ext.MessageBox.confirm('提示', '您确定要暂停该任务', function(id){
                                    if(id=='yes'){
                                    var grid=me.up("grid");
                                    var selectionList=grid.getSelectionModel().getSelection();
                                    var selection = selectionList[0];
                                    var raw= selection.getData();
                                    Ext.Ajax.request({
                                        url: '/task/pause.json',
                                        params: {
                                            id: raw.id
                                        },
                                        success: function(response){
                                            var _response = Ext.JSON.decode(response.responseText);
                                            Ext.MessageBox.alert(Ext.ENV.MSG.title,_response.msg,function(){
                                                grid.getStore().reload();
                                                grid.getSelectionModel().clearSelections();
                                                grid.fireEvent("selectionchange",null,grid.getSelectionModel());
                                            });
                                        }
                                    });
                                    }
                                });
                            },
                            disabled: true,
                            id: 'pause',
                            text: '暂停'
                        },
                        {
                            xtype: 'button',
                            handler: function(button, e) {
                                var me=this;
                                Ext.MessageBox.confirm('提示', '您确定要删除该任务', function(id){
                                if(id=='yes'){
                                var grid=me.up("grid");
                                var selectionList=grid.getSelectionModel().getSelection();
                                var selection = selectionList[0];
                                var raw= selection.getData();
                                Ext.Ajax.request({
                                    url: '/task/delete.json',
                                    params: {
                                        id: raw.id
                                    },
                                    success: function(response){
                                        var _response = Ext.JSON.decode(response.responseText);
                                        Ext.MessageBox.alert(Ext.ENV.MSG.title,_response.msg,function(){
                                            grid.getStore().reload();
                                            grid.getSelectionModel().clearSelections();
                                            grid.fireEvent("selectionchange",null,grid.getSelectionModel());
                                        });
                                    }
                                });
                                }
                                });
                            },
                            disabled: true,
                            id: 'remove',
                            text: '删除'
                        }
                    ]
                },
                {
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    width: 360,
                    displayInfo: true,
                    store: 'TaskEntityStore'
                }
            ],
            listeners: {
                selectionchange: {
                    fn: me.onGridpanelSelectionChange,
                    scope: me
                },
                itemdblclick:{
                    fn: me.onItemdblclick,
                    scope: me
                }
            }
        });

        me.callParent(arguments);
    },

    onButtonClick: function(button, e, eOpts) {
        var taskEntityWindow = Ext.create('FQServiceApp.view.CreateTaskEntityWindow');
        taskEntityWindow.show();
    },
    onLogButtonClick: function(button, e, eOpts) {
        var grid=button.up("grid");
        var selectionList=grid.getSelectionModel().getSelection();
        var selection = selectionList[0];
        var id;
        if(selection!=undefined){
            var raw= selection.getData();
            id = raw.id;
        }
        var logWindow = Ext.create('FQServiceApp.view.CreateTaskLogWindow',{
            extraParams:{
                taskId:id
            }
        });
        logWindow.show();
    },
    onGridpanelSelectionChange: function(model, selected, eOpts) {
        this.down('#remove').setDisabled(!selected.length);
        this.down('#start').setDisabled(!selected.length);
        this.down('#pause').setDisabled(!selected.length);
    },
    onItemdblclick:function(view,record,item){
        var taskEntityWindow = Ext.create('FQServiceApp.view.CreateTaskEntityWindow',{record:record});
        taskEntityWindow.show();
    }

});