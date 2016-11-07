Ext.define('FQServiceApp.model.TaskLog', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.Field'
    ],

    fields: [
        {
            name: 'taskName'
        },
        {
            name: 'action'
        },
        {
            name: 'description'
        },{
            name: 'dateCreated'
        }
    ]
});