Ext.define('FQServiceApp.view.LoginWindow', {
    extend: 'Ext.window.Window',
    requires: [
        'Ext.form.Panel',
        'Ext.form.field.Text',
        'Ext.data.proxy.JsonP',
        'Ext.data.Operation'
    ],
    height: 160,
    width: 400,
    closable:false,
    title: '登录FQ应用管理平台',
    initComponent: function() {
        var me = this;
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    height: 80,
                    bodyPadding: 10,
                    header: false,
                    items: [
                        {
                            xtype: 'textfield',
                            name:"key",
                            anchor: '80%',
                            fieldLabel: '账号'
                        },
                        {
                            xtype: 'textfield',
                            anchor: '80%',
                            name:"pwd",
                            fieldLabel: '密码',
                            inputType:"password"
                        }
                    ]
                }
            ],
            dockedItems: [{
                xtype: 'toolbar',
                dock: 'bottom',
                ui: 'footer',
                items: [
                    { xtype: 'component', flex: 1 },
                    { xtype: 'button', text: '登录',handler:function(){
                        var form =  this.up('window').down("form").getForm();
                        var window=this.up('window');
                        var jsonPClient= new Ext.data.proxy.JsonP();
                        var operation=new Ext.data.Operation({
                            params:form.getValues(),
                            action:Ext.ENV.loginUrl,
                            url:Ext.ENV.loginUrl
                        });
                        jsonPClient.doRequest(operation);
                    } }
                ]
            }]
        });

        me.callParent(arguments);
    }

});
