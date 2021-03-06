/*
 * File: app/model/TaskEntity.js
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

Ext.define('FQServiceApp.model.TaskEntity', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.Field'
    ],

    fields: [
        {
            name: 'name'
        },
        {
            name: 'type'
        },
        {
            name: 'app'
        },
        {
            name: 'cron'
        },
        {
            name: 'preExecTime'
        },
        {
            name: 'nextExecTime'
        },
        {
            name: 'status'
        },
        {
            name: 'uri'
        },
        {
            name: 'description'
        },
        {
            name: 'statusDescription'
        },
        {
            name: 'author'
        }
    ]
});