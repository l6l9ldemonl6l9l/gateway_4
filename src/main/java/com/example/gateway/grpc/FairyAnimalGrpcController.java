package com.example.gateway.grpc;

import com.example.FairyAnimal.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class FairyAnimalGrpcController extends FairyAnimalServiceGrpc.FairyAnimalServiceImplBase {

    private String url = "example-fairyAnimals";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 7082).usePlaintext().build();
    private FairyAnimalServiceGrpc.FairyAnimalServiceBlockingStub stub = FairyAnimalServiceGrpc.newBlockingStub(channel);

    @Override
    public void all(AllFairyAnimalRequest request, StreamObserver<AllFairyAnimalResponse> responseObserver) {
        AllFairyAnimalRequest allFairyAnimalsRequest = AllFairyAnimalRequest.newBuilder().build();
        AllFairyAnimalResponse response = stub.all(allFairyAnimalsRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(FairyAnimalRequest request, StreamObserver<FairyAnimalResponse> responseObserver) {
        FairyAnimalRequest FairyAnimal = FairyAnimalRequest.newBuilder().
                setName(request.getName()).
                setEnergy(request.getEnergy()).
                setSizeMind(request.getSizeMind()).
                build();
        FairyAnimalResponse response = stub.add(FairyAnimal);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(FairyAnimalByIdRequest request, StreamObserver<FairyAnimalResponse> responseObserver) {
        FairyAnimalByIdRequest FairyAnimal = FairyAnimalByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        FairyAnimalResponse response = stub.byId(FairyAnimal);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(FairyAnimalByIdRequest request, StreamObserver<DeleteFairyAnimalResponse> responseObserver) {
        FairyAnimalByIdRequest FairyAnimal = FairyAnimalByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteFairyAnimalResponse response = stub.delete(FairyAnimal);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

