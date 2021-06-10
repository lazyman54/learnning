package com.ek.study.designPattern;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/29
 */
public class FilterChainPatternStudy {
    public static void main(String[] args) {
        AbstractFilter worker1 = new FilterWorker1();
        AbstractFilter worker2 = new FilterWorker2();

        worker1.setNextFilter(worker2);

        worker1.handlerRequest("123", "1234", null);


    }
}


class ChainHandler {

    private final Filter[] filters = new Filter[0];

    private int pos;

    private int count;


    public void addFilter(Filter filter) {

    }


    public void doFilter(String request, String response) {
        Filter filter = filters[pos++];
        if (filter != null) {
            filter.invoke(request, response, this);
        }
    }

}

interface Filter {

    int order();

    void invoke(String request, String response, ChainHandler chainHandler);
}

abstract class AbstractFilter implements Filter {

    private AbstractFilter nextFilter;

    protected void setNextFilter(AbstractFilter nextFilter) {
        this.nextFilter = nextFilter;
    }

    protected void handlerRequest(String request, String response, ChainHandler chainHandler) {

        if (request.equals(response) || this.order() == 2) {
            this.invoke(request, response, chainHandler);
        } else {
            if (nextFilter != null) {
                nextFilter.handlerRequest(request, response, chainHandler);
            }
        }
    }
}

class FilterWorker1 extends AbstractFilter {
    @Override
    public int order() {
        return 1;
    }

    @Override
    public void invoke(String request, String response, ChainHandler chainHandler) {
        System.out.println("worker 1 work");
    }
}

class FilterWorker2 extends AbstractFilter {
    @Override
    public int order() {
        return 2;
    }

    @Override
    public void invoke(String request, String response, ChainHandler chainHandler) {
        System.out.println("worker 2 work");
    }
}
