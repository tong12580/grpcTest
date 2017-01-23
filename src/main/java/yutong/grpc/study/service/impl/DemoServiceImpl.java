package yutong.grpc.study.service.impl;

import io.grpc.stub.StreamObserver;
import yutong.grpc.study.dto.Person;
import yutong.grpc.study.dto.PersonList;
import yutong.grpc.study.dto.PingRequest;
import yutong.grpc.study.dto.PingResponse;
import yutong.grpc.study.dto.QueryParameter;
import yutong.grpc.study.service.DemoServiceGrpc.DemoServiceImplBase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2016/12/7 16:08
 */
public class DemoServiceImpl extends DemoServiceImplBase {

    @Override
    public void ping(PingRequest pingRequest, StreamObserver<PingResponse> streamObserver) {
        super.ping(pingRequest, streamObserver);
        PingResponse reply = PingResponse.newBuilder().setOut("pong => " + pingRequest.getIn()).build();
        streamObserver.onNext(reply);
        streamObserver.onCompleted();
    }

    @Override
    public void getPersonList(QueryParameter queryParameter, StreamObserver<PersonList> streamObserver) {
        super.getPersonList(queryParameter, streamObserver);
        PersonList.Builder personListBuilder = PersonList.newBuilder();
        Person.Builder builder = Person.newBuilder();
        List<Person> list = new ArrayList<Person>();
        for (short i = 0; i < 10; i++) {
            list.add(builder.setAge(i).setChildrenCount(i).setName("test" + i).setSex(true).build());
        }
        personListBuilder.addAllItems(list);
        streamObserver.onNext(personListBuilder.build());
        streamObserver.onCompleted();
    }
}
