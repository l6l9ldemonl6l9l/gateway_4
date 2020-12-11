package com.example.gateway.grpc;

import com.example.Deity.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class DeityGrpcController extends DeityServiceGrpc.DeityServiceImplBase {

    private String url = "example-deitys";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 7083).usePlaintext().build();
    private DeityServiceGrpc.DeityServiceBlockingStub stub = DeityServiceGrpc.newBlockingStub(channel);

    @Override
    public void all(AllDeityRequest request, StreamObserver<AllDeityResponse> responseObserver) {
        AllDeityRequest allDeitysRequest = AllDeityRequest.newBuilder().build();
        AllDeityResponse response = stub.all(allDeitysRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(DeityRequest request, StreamObserver<DeityResponse> responseObserver) {
        DeityRequest Deity = DeityRequest.newBuilder().
                setName(request.getName()).
                setAge(request.getAge()).
                build();
        DeityResponse response = stub.add(Deity);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(DeityByIdRequest request, StreamObserver<DeityResponse> responseObserver) {
        DeityByIdRequest Deity = DeityByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        DeityResponse response = stub.byId(Deity);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeityByIdRequest request, StreamObserver<DeleteDeityResponse> responseObserver) {
        DeityByIdRequest Deity = DeityByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteDeityResponse response = stub.delete(Deity);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
