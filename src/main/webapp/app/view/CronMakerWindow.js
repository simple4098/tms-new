/*
 * File: app/view/CronMakerWindow.js
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

Ext.define('FQServiceApp.view.CronMakerWindow', {
    extend: 'Ext.window.Window',

    requires: [
        'Ext.tab.Panel',
        'Ext.tab.Tab',
        'Ext.form.field.Number',
        'Ext.form.Label',
        'Ext.form.field.Time',
        'Ext.form.CheckboxGroup',
        'Ext.form.field.Radio',
        'Ext.form.field.ComboBox'
    ],

    modal:true,
    height: 281,
    width: 494,
    title: '克隆表达式生成器',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'tabpanel',
                    height: 174,
                    id: 'CronMakerWindowTabPanel',
                    activeTab: 0,
                    items: [
                        {
                            xtype: 'panel',
                            height: 236,
                            id: 'CronMakerWindowMinuteTab',
                            padding: '10px 0 0 5px',
                            title: '分',
                            layout: 'absolute',
                            tabConfig: {
                                xtype: 'tab',
                                width: 60
                            },
                            items: [
                                        {
                                            id:'CronMakerWindowMinuteTabCycle',
                                            xtype:'radio',
                                            name:'CronMakerWindowMinuteTabRadioStrategy',
                                            checked:true,
                                            boxLabel:'周期:',
                                            handler:function(curCmp){
                                                curCmp.nextSibling().setDisabled(!curCmp.checked);
                                                Ext.getCmp("CronMakerWindowMinuteTabAssign").fireEvent("change");
                                            }
                                        },
                                        {
                                            xtype: 'container',
                                            height: 30,
                                            x:65,
                                            y:0,
                                            layout: 'column',
                                            items: [
                                                {
                                                    xtype: 'numberfield',
                                                    id: 'CronMakerWindowMinuteTabInputStart',
                                                    width: 100,
                                                    fieldLabel: '从',
                                                    labelWidth: 30,
                                                    value: 0,
                                                    maxValue: 59,
                                                    editable: false,
                                                    minValue:0
                                                },
                                                {
                                                    xtype: 'label',
                                                    margin: '5px 0 0 10px',
                                                    text: '分开始，'
                                                },
                                                {
                                                    xtype: 'numberfield',
                                                    id: 'CronMakerWindowMinuteTabInputCycle',
                                                    width: 100,
                                                    fieldLabel: '每',
                                                    editable: false,
                                                    labelWidth: 30,
                                                    value: 1,
                                                    minValue:1,
                                                    maxValue: 59
                                                },
                                                {
                                                    xtype: 'label',
                                                    margin: '5px 0 0 10px',
                                                    text: '分执行'
                                                }
                                            ]
                                        },

                                {
                                    xtype:'radio',
                                    id:'CronMakerWindowMinuteTabAssign',
                                    name:'CronMakerWindowMinuteTabRadioStrategy',
                                    boxLabel:'指定:',
                                    x:0,
                                    y:40,
                                    handler:function(curCmp){
                                        curCmp.nextSibling().setDisabled(!curCmp.checked);
                                        Ext.getCmp("CronMakerWindowMinuteTabCycle").fireEvent("change");
                                    }
                                },
                                {
                                    xtype: 'container',
                                    disabled:true,
                                    height: 30,
                                    x:65,
                                    y:40,
                                    layout: 'column',
                                    items:[
                                        {
                                            xtype: 'combobox',
                                            id:"CronMakerWindowMinuteTabInputAssign",
                                            width: 300,
                                            allowBlank: false,
                                            editable: false,
                                            multiSelect: true,
                                            store: [
                                                0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
                                                21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,
                                                39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,
                                                57,58,59
                                            ],
                                            x:70,
                                            y:40
                                        }
                                    ]
                                }

                            ]
                        },
                        {
                            xtype: 'panel',
                            id: 'CronMakerWindowHourlyTab',
                            title: '时',
                            layout: 'absolute',
                            padding: '10px 0 0 5px',
                            tabConfig: {
                                xtype: 'tab',
                                width: 60
                            },
                            items: [
                                {
                                    id:'CronMakerWindowHourlyTabCycle',
                                    xtype:'radio',
                                    name:'CronMakerWindowTabPanelHourlyTabRadioStrategy',
                                    checked:true,
                                    boxLabel:'周期:',
                                    handler:function(curCmp){
                                        curCmp.nextSibling().setDisabled(!curCmp.checked);
                                        Ext.getCmp("CronMakerWindowHourlyTabAssign").fireEvent("change");
                                    }
                                },
                                {
                                    xtype: 'container',
                                    height: 30,
                                    x:65,
                                    y:0,
                                    layout: 'column',
                                    items: [
                                        {
                                            xtype: 'numberfield',
                                            id: 'CronMakerWindowHourlyTabInputStart',
                                            width: 100,
                                            fieldLabel: '从',
                                            labelWidth: 30,
                                            value: 0,
                                            maxValue: 59,
                                            editable: false,
                                            minValue:0
                                        },
                                        {
                                            xtype: 'label',
                                            margin: '5px 0 0 10px',
                                            text: '时开始，'
                                        },
                                        {
                                            xtype: 'numberfield',
                                            id: 'CronMakerWindowHourlyTabInputCycle',
                                            width: 100,
                                            fieldLabel: '每',
                                            editable: false,
                                            labelWidth: 30,
                                            value: 1,
                                            minValue:1,
                                            maxValue: 59
                                        },
                                        {
                                            xtype: 'label',
                                            margin: '5px 0 0 10px',
                                            text: '小时执行'
                                        }
                                    ]
                                },

                                {
                                    xtype:'radio',
                                    id:'CronMakerWindowHourlyTabAssign',
                                    name:'CronMakerWindowTabPanelHourlyTabRadioStrategy',
                                    boxLabel:'指定:',
                                    x:0,
                                    y:40,
                                    handler:function(curCmp){
                                        curCmp.nextSibling().setDisabled(!curCmp.checked);
                                        Ext.getCmp("CronMakerWindowHourlyTabCycle").fireEvent("change");
                                    }
                                },
                                {
                                    xtype: 'container',
                                    disabled:true,
                                    height: 30,
                                    x:65,
                                    y:40,
                                    layout: 'column',
                                    items:[
                                        {
                                            xtype: 'combobox',
                                            id:"CronMakerWindowHourlyTabInputAssign",
                                            width: 300,
                                            allowBlank: false,
                                            editable: false,
                                            multiSelect: true,
                                            store: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
                                            x:70,
                                            y:40
                                        }
                                    ]
                                }

                            ]
                        },
                        {
                            xtype: 'panel',
                            id: 'CronMakerWindowDailyTab',
                            title: '日',
                            layout: 'absolute',
                            padding: '10px 0 0 5px',
                            tabConfig: {
                                xtype: 'tab',
                                width: 60
                            },
                            items: [
                                {
                                    id:'CronMakerWindowDailyTabCycle',
                                    xtype:'radio',
                                    name:'CronMakerWindowTabPanelDailyTabRadioStrategy',
                                    checked:true,
                                    boxLabel:'周期:',
                                    handler:function(curCmp){
                                        curCmp.nextSibling().setDisabled(!curCmp.checked);
                                        Ext.getCmp("CronMakerWindowDailyTabAssign").fireEvent("change");
                                    }
                                },
                                {
                                    xtype: 'container',
                                    height: 30,
                                    x:65,
                                    y:0,
                                    layout: 'column',
                                    items: [
                                        {
                                            xtype: 'numberfield',
                                            id: 'CronMakerWindowDailyTabInputStart',
                                            width: 100,
                                            fieldLabel: '从',
                                            labelWidth: 30,
                                            value: 0,
                                            maxValue: 31,
                                            editable: false,
                                            minValue:0
                                        },
                                        {
                                            xtype: 'label',
                                            margin: '5px 0 0 10px',
                                            text: '号开始，'
                                        },
                                        {
                                            xtype: 'numberfield',
                                            id: 'CronMakerWindowDailyTabInputCycle',
                                            width: 100,
                                            fieldLabel: '每',
                                            editable: false,
                                            labelWidth: 30,
                                            value: 1,
                                            minValue:1,
                                            maxValue: 31
                                        },
                                        {
                                            xtype: 'label',
                                            margin: '5px 0 0 10px',
                                            text: '天执行'
                                        }
                                    ]
                                },

                                {
                                    xtype:'radio',
                                    id:'CronMakerWindowDailyTabAssign',
                                    name:'CronMakerWindowTabPanelDailyTabRadioStrategy',
                                    boxLabel:'指定:',
                                    x:0,
                                    y:40,
                                    handler:function(curCmp){
                                        curCmp.nextSibling().setDisabled(!curCmp.checked);
                                        Ext.getCmp("CronMakerWindowDailyTabCycle").fireEvent("change");
                                    }
                                },
                                {
                                    xtype: 'container',
                                    disabled:true,
                                    height: 30,
                                    x:65,
                                    y:40,
                                    layout: 'column',
                                    items:[
                                        {
                                            xtype: 'combobox',
                                            id:"CronMakerWindowDailyTabInputAssign",
                                            width: 300,
                                            allowBlank: false,
                                            editable: false,
                                            multiSelect: true,
                                            store: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31],
                                            x:70,
                                            y:40
                                        }
                                    ]
                                }

                            ]
                        },
                        {
                            xtype: 'panel',
                            id: 'CronMakerWindowWeekTab',
                            title: '周',
                            layout: 'absolute',
                            padding: '10px 0 0 5px',
                            tabConfig: {
                                xtype: 'tab',
                                width: 60
                            },
                            items: [
                                {
                                    id:'CronMakerWindowWeekTabCycle',
                                    xtype:'radio',
                                    name:'CronMakerWindowTabPanelWeekTabRadioStrategy',
                                    checked:true,
                                    boxLabel:'周期:',
                                    handler:function(curCmp){
                                        curCmp.nextSibling().setDisabled(!curCmp.checked);
                                        Ext.getCmp("CronMakerWindowWeekTabAssign").fireEvent("change");
                                    }
                                },
                                {
                                    xtype: 'container',
                                    height: 30,
                                    x:65,
                                    y:0,
                                    layout: 'column',
                                    items: [
                                        {
                                            xtype: 'numberfield',
                                            id: 'CronMakerWindowWeekTabInputStart',
                                            width: 100,
                                            fieldLabel: '从周',
                                            labelWidth: 30,
                                            value: 0,
                                            maxValue: 7,
                                            editable: false,
                                            minValue:0
                                        },
                                        {
                                            xtype: 'label',
                                            margin: '5px 0 0 10px',
                                            text: '开始，'
                                        },
                                        {
                                            xtype: 'numberfield',
                                            id: 'CronMakerWindowWeekTabInputCycle',
                                            width: 100,
                                            fieldLabel: '每',
                                            editable: false,
                                            labelWidth: 30,
                                            value: 1,
                                            minValue:1,
                                            maxValue: 7
                                        },
                                        {
                                            xtype: 'label',
                                            margin: '5px 0 0 10px',
                                            text: '周执行'
                                        }
                                    ]
                                },

                                {
                                    xtype:'radio',
                                    id:'CronMakerWindowWeekTabAssign',
                                    name:'CronMakerWindowTabPanelWeekTabRadioStrategy',
                                    boxLabel:'指定:',
                                    x:0,
                                    y:40,
                                    handler:function(curCmp){
                                        curCmp.nextSibling().setDisabled(!curCmp.checked);
                                        Ext.getCmp("CronMakerWindowWeekTabCycle").fireEvent("change");
                                    }
                                },
                                {
                                    xtype: 'container',
                                    disabled:true,
                                    height: 30,
                                    x:65,
                                    y:40,
                                    layout: 'column',
                                    items:[
                                        {
                                            xtype: 'combobox',
                                            id:"CronMakerWindowWeekTabInputAssign",
                                            width: 300,
                                            allowBlank: false,
                                            editable: false,
                                            multiSelect: true,
                                            store: [
                                                [
                                                    '1',
                                                    '周一'
                                                ],
                                                [
                                                    '2',
                                                    '周二'
                                                ],
                                                [
                                                    '3',
                                                    '周三'
                                                ],
                                                [
                                                    '4',
                                                    '周四'
                                                ],
                                                [
                                                    '5',
                                                    '周五'
                                                ],
                                                [
                                                    '6',
                                                    '周六'
                                                ],
                                                [
                                                    '7',
                                                    '周日'
                                                ]
                                            ],
                                            x:70,
                                            y:40
                                        }
                                    ]
                                }

                            ]
                        },
                        {
                            xtype: 'panel',
                            id: 'CronMakerWindowMonthTab',
                            title: '月',
                            layout: 'absolute',
                            padding: '10px 0 0 5px',
                            tabConfig: {
                                xtype: 'tab',
                                width: 60
                            },
                            items: [
                                {
                                    id:'CronMakerWindowMonthTabCycle',
                                    xtype:'radio',
                                    name:'CronMakerWindowTabPanelMonthTabRadioStrategy',
                                    checked:true,
                                    boxLabel:'周期:',
                                    handler:function(curCmp){
                                        curCmp.nextSibling().setDisabled(!curCmp.checked);
                                        Ext.getCmp("CronMakerWindowMonthTabAssign").fireEvent("change");
                                    }
                                },
                                {
                                    xtype: 'container',
                                    height: 30,
                                    x:65,
                                    y:0,
                                    layout: 'column',
                                    items: [
                                        {
                                            xtype: 'numberfield',
                                            id: 'CronMakerWindowMonthTabInputStart',
                                            width: 100,
                                            fieldLabel: '从',
                                            labelWidth: 30,
                                            value: 0,
                                            maxValue: 12,
                                            editable: false,
                                            minValue:0
                                        },
                                        {
                                            xtype: 'label',
                                            margin: '5px 0 0 10px',
                                            text: '月开始，'
                                        },
                                        {
                                            xtype: 'numberfield',
                                            id: 'CronMakerWindowMonthTabInputCycle',
                                            width: 100,
                                            fieldLabel: '每',
                                            editable: false,
                                            labelWidth: 30,
                                            value: 1,
                                            minValue:1,
                                            maxValue: 12
                                        },
                                        {
                                            xtype: 'label',
                                            margin: '5px 0 0 10px',
                                            text: '月执行'
                                        }
                                    ]
                                },

                                {
                                    xtype:'radio',
                                    id:'CronMakerWindowMonthTabAssign',
                                    name:'CronMakerWindowTabPanelMonthTabRadioStrategy',
                                    boxLabel:'指定:',
                                    x:0,
                                    y:40,
                                    handler:function(curCmp){
                                        curCmp.nextSibling().setDisabled(!curCmp.checked);
                                        Ext.getCmp("CronMakerWindowMonthTabCycle").fireEvent("change");
                                    }
                                },
                                {
                                    xtype: 'container',
                                    disabled:true,
                                    height: 30,
                                    x:65,
                                    y:40,
                                    layout: 'column',
                                    items:[
                                        {
                                            xtype: 'combobox',
                                            id:"CronMakerWindowMonthTabInputAssign",
                                            width: 300,
                                            allowBlank: false,
                                            editable: false,
                                            multiSelect: true,
                                            store: [
                                                1,2,3,4,5,6,7,8,9,10,11,12
                                            ],
                                            x:70,
                                            y:40
                                        }
                                    ]
                                }

                            ]
                        }
                    ]
                },
                {
                    xtype: 'container',
                    height: 48,
                    layout: 'absolute',
                    items: [
                        {
                            xtype: 'button',
                            handler: function(button, e) {
                                var currentWindow=this.up("window");

                                var tabPanel=Ext.getCmp("CronMakerWindowTabPanel");

                                var activeTab=tabPanel.getActiveTab();

                                var CRON_SECOND_INDEX=0;

                                var CRON_MINUTE_INDEX=1;

                                var CRON_HOUR_INDEX=2;

                                var CRON_DAY_INDEX=3;

                                var CRON_MONTH_INDEX=4;

                                var CRON_WEEK_INDEX=5;

                                var CRON_YEAR_INDEX=6;


                                var cronArray = ["0","*","*","*","*","?",""];

                                var markCron=function(){

                                    cron="";

                                    for(i=0;i<cronArray.length;i++){
                                         var cur=cronArray[i];

                                        cron=cron+(cur=="0/1"&&i!=CRON_HOUR_INDEX&&i!=CRON_MINUTE_INDEX&&i!=CRON_SECOND_INDEX?"*":cur)+" ";

                                    }
                                    return cron;
                                };

                                var cronMakerFunc=function(panelMarker,cronIndex,call){
                                    //如果选择的是周期
                                    var cycleRadioId=panelMarker+"Cycle";
                                    var assignRadioId=panelMarker+"Assign";
                                    var value='*';
                                    if(Ext.getCmp(cycleRadioId).checked){
                                        var secondInputStart =Ext.getCmp(panelMarker+"InputStart").getValue();
                                        var secondsInputCycle = Ext.getCmp(panelMarker+"InputCycle").getValue();
                                        value=secondInputStart+"/"+secondsInputCycle;
                                    }
                                    //如果分钟面板 选择的是特定的分钟
                                    if(Ext.getCmp(assignRadioId).checked){
                                        value= Ext.getCmp(panelMarker+'InputAssign').getValue().join();
                                    }
                                    cronArray[cronIndex]=value;
                                    if(typeof call!='undefined'){
                                        call(value);
                                    }
                                };





                                var cronExpInput=Ext.getCmp("CreateTaskEntityWindowCronExp");
                                //每分钟的策略
                                cronMakerFunc("CronMakerWindowMinuteTab",CRON_MINUTE_INDEX);
                                //小时策略
                                cronMakerFunc("CronMakerWindowHourlyTab",CRON_HOUR_INDEX);
                                //天策略
                                cronMakerFunc("CronMakerWindowDailyTab",CRON_DAY_INDEX);
                                //周策略
                                cronMakerFunc("CronMakerWindowWeekTab",CRON_WEEK_INDEX,function(value){
                                    if(value!='0/1') {
                                        cronArray[CRON_DAY_INDEX] = '?';
                                    }else{
                                        cronArray[CRON_WEEK_INDEX] = '?';
                                    }
                                });

                                //月策略
                                cronMakerFunc("CronMakerWindowMonthTab",CRON_MONTH_INDEX);

                                cronExpInput.setValue(markCron());

                                currentWindow.close();


                            },
                            x: 340,
                            y: 10,
                            height: 30,
                            width: 110,
                            text: '生成'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});