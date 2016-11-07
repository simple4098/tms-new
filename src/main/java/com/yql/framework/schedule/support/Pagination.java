package com.yql.framework.schedule.support;


import com.yql.framework.schedule.utils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxiaohong
 */
public final class Pagination<D> implements Map,Describable {
        private int totalCount;
        private int offset;
        private int pageSize = 10;
        private int pageNo = 1; //当前页
        private Map internalMap = new HashMap();
        private D dto;
        public Pagination(D dto) {
            this.dto = dto;
            offset = (pageNo - 1) * pageSize;
        }

        public Pagination(int pageSize, int currentPage) {
            this.pageSize = pageSize;
            this.pageNo = currentPage;
            this.offset = (pageNo - 1) * this.pageSize;
        }

        public Pagination(int pageNo, int pageSize, D dto) {
            this(dto);
            this.pageNo = pageNo;
            this.pageSize = pageSize;
            this.offset = (pageNo - 1) * this.pageSize;
        }

        public Pagination(int pageNo, D dto) {
            this(dto);
            this.pageNo = pageNo;
            this.offset = (pageNo - 1) * this.pageSize;
        }

        /**
         * describe the inner dto object to a map, and put all entities into inner map.
         * Normally, the client should not invoke this method unless pretty necessary
         */
        public void describeDto(){
            if (this.dto != null) {
                internalMap.putAll(BeanUtils.describe(this.dto));
            }
        }

        public void describe(){
            this.describeDto();
        }


        public int getPageSize() {
            return pageSize;
        }

        public int getPageNo() {
            return pageNo;
        }

        public int getOffset() {
            return offset;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public int getTotalPages() {
            return totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        }


        @Override
        public int size() {
            return internalMap.size();
        }

        @Override
        public boolean isEmpty() {
            return internalMap.isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            return internalMap.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return internalMap.containsValue(value);
        }

        @Override
        public Object get(Object key) {
            return internalMap.get(key);
        }

        @Override
        public Object put(Object key, Object value) {
            throw new UnsupportedOperationException("对象不支持方法!");
        }

        @Override
        public Object remove(Object key) {
            return internalMap.remove(key);
        }

        @Override
        public void putAll(Map m) {
            throw new UnsupportedOperationException("对象不支持方法!");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("对象不支持方法!");
        }

        @Override
        public java.util.Set keySet() {
            return internalMap.keySet();
        }

        @Override
        public java.util.Collection values() {
            return internalMap.values();
        }

        @Override
        public java.util.Set<Entry> entrySet() {
            return internalMap.entrySet();
        }

        public D getDto() {
            return dto;
        }
}
