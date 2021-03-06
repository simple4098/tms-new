/*
 * File: app/view/MainView.js
 *
 * This file was generated by Sencha Architect version 3.0.1.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('FQServiceApp.view.MainView', {
    extend: 'Ext.container.Viewport',

    requires: [
        'Ext.menu.Menu',
        'Ext.menu.Item',
        'Ext.grid.Panel',
        'Ext.grid.View',
        'Ext.grid.column.Column',
        'Ext.button.Button',
        'FQServiceApp.view.TaskGridPanel',
        'Ext.toolbar.Paging'
    ],

    itemId: 'mainView',
    layout: 'border',

    initComponent: function() {
        var me = this;
        me.on("afterlayout",function(){
            Ext.getCmp("sideBar").items.each(function(item){
                if(item.autoOpen){
                    me.createTab(item);
                }
            });
            Ext.getCmp("sideBar").on("click",function(menu, item, e, eOpts){
                me.createTab(item);
                location.hash = item.itemId;
            });
        });

        Ext.applyIf(me, {
            createTab:function(item){
                var mainContentPanel=Ext.getCmp("mainContentPanel");
                var existTabItems=Ext.getCmp("mainContentPanel").items;
                var uri=item.uri;
                var tabId ="tab_"+uri;
                var createTabNecessary = true;
                existTabItems.each(function(item){
                    if(item.getItemId()==tabId){
                        createTabNecessary=false;
                    }
                });
                if(createTabNecessary){
                    var title=item.text;
                    mainContentPanel.add(
                        {
                            xtype: uri,
                            itemId: tabId,
                            title: title
                        }
                    );
                    mainContentPanel.setActiveTab(tabId);
                }

            },
            items: [
                {
                    xtype:"panel",
                    region:"north",
                    bodyStyle:"background-color:rgb(56, 146, 211)",
                    header:false,
                    height:30,
                    title:"应用管理系统",
                    html:"<div style='color: white;font-weight: bolder;'><span style='float: left;margin-left: 10px;margin-top: 4px;'>欢迎使用任务管理系统</span><span id='loginButton' style='float: right;margin-top: 4px;margin-right: 10px;cursor: pointer'>" +
                        "登录</span></div>"
                },

                {
                    xtype: 'panel',
                    margin:"5 0 0 0",
                    region: 'west',
                    split: true,
                    itemId: 'menuPanel',
                    width: 150,
                    title: '系统菜单',
                    splitterResize:false,
                    collapsible:true,
                    items: [
                        {
                            xtype: 'menu',
                            id:"sideBar",
                            floating: false,
                            items: [
                                {
                                    xtype: 'menuitem',
                                    itemId: 'home',
                                    autoOpen:true,
                                    uri:"taskgridpanel",
                                    padding:"5px 0 0 0",
                                    margin:"0 0 0 -18px",
                                    text: '任务管理'
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'tabpanel',
                    region: 'center',
                    id:"mainContentPanel",
                    itemId: 'mainContentPanel',
                    margin:"5 0 0 0",
                    activeTab: 0,
                    layout: 'card'

                }
            ]

        });

        me.callParent(arguments);
    }

});