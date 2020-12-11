package com.example.gateway.grpc;

import com.example.Terrain.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class TerrainGrpcController extends TerrainServiceGrpc.TerrainServiceImplBase {

    private String url = "example-terrains";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 7084).usePlaintext().build();
    private TerrainServiceGrpc.TerrainServiceBlockingStub stub = TerrainServiceGrpc.newBlockingStub(channel);

    @Override
    public void all(AllTerrainRequest request, StreamObserver<AllTerrainResponse> responseObserver) {
        AllTerrainRequest allTerrainsRequest = AllTerrainRequest.newBuilder().build();
        AllTerrainResponse response = stub.all(allTerrainsRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(TerrainRequest request, StreamObserver<TerrainResponse> responseObserver) {
        TerrainRequest Terrain = TerrainRequest.newBuilder().
                setName(request.getName()).
                setSizeBeauty(request.getSizeBeauty()).
                build();
        TerrainResponse response = stub.add(Terrain);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(TerrainByIdRequest request, StreamObserver<TerrainResponse> responseObserver) {
        TerrainByIdRequest Terrain = TerrainByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        TerrainResponse response = stub.byId(Terrain);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(TerrainByIdRequest request, StreamObserver<DeleteTerrainResponse> responseObserver) {
        TerrainByIdRequest Terrain = TerrainByIdRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteTerrainResponse response = stub.delete(Terrain);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
