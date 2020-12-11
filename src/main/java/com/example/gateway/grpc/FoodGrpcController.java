package com.example.gateway.grpc;

import com.example.Food.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class FoodGrpcController extends FoodServiceGrpc.FoodServiceImplBase {

    private String url = "example-foods";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 7081).usePlaintext().build();
    private FoodServiceGrpc.FoodServiceBlockingStub stub = FoodServiceGrpc.newBlockingStub(channel);

    @Override
    public void all(AllFoodRequest request, StreamObserver<AllFoodResponse> responseObserver) {
        AllFoodRequest allFoodsRequest = AllFoodRequest.newBuilder().build();
        AllFoodResponse response = stub.all(allFoodsRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(FoodRequest request, StreamObserver<FoodResponse> responseObserver) {
        FoodRequest Food = FoodRequest.newBuilder().
                setKindFood(request.getKindFood()).
                setTaste(request.getTaste()).
                build();
        FoodResponse response = stub.add(Food);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(FoodByIdRequest request, StreamObserver<FoodResponse> responseObserver) {
        FoodByIdRequest Food = FoodByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        FoodResponse response = stub.byId(Food);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(FoodByIdRequest request, StreamObserver<DeleteFoodResponse> responseObserver) {
        FoodByIdRequest Food = FoodByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteFoodResponse response = stub.delete(Food);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
