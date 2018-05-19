import io.vertx.core.AbstractVerticle
import io.vertx.core.Future

class MyFirstVerticle : AbstractVerticle() {
    override fun start(startFuture: Future<Void>?) {
        vertx
                .createHttpServer()
                .requestHandler { it.response().end("<h1>Hello from my first Vert.x 3 application</h1>") }
                .listen(888) {
                    if (it.succeeded()) {
                        startFuture!!.complete()
                    } else {
                        startFuture!!.fail(it.cause())
                    }
                }
    }
}