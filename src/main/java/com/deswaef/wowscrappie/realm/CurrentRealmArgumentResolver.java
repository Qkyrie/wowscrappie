package com.deswaef.wowscrappie.realm;

import com.deswaef.wowscrappie.realm.domain.Realm;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;
import java.util.Optional;

public final class CurrentRealmArgumentResolver implements
        HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return findMethodAnnotation(CurrentRealm.class, parameter) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Object currentRealm = webRequest.getAttribute("com.wowscrappie.default-realm",
                NativeWebRequest.SCOPE_SESSION);

        if (parameter.getParameterType().isAssignableFrom(Optional.class)) {
            if (currentRealm == null) {
                return Optional.empty();
            } else {
                return Optional.of((Realm) currentRealm);
            }
        } else if (parameter.getParameterType().isAssignableFrom(Realm.class)) {
            if (currentRealm == null) {
                return null;
            } else {
                return currentRealm;
            }
        } else {
            if (currentRealm == null) {
                return null;
            } else {
                throw new ClassCastException(currentRealm + " is not assignable to "
                        + parameter.getParameterType());
            }
        }
    }

    /**
     * Obtains the specified {@link Annotation} on the specified {@link MethodParameter}.
     *
     * @param annotationClass the class of the {@link Annotation} to find on the
     *                        {@link MethodParameter}
     * @param parameter       the {@link MethodParameter} to search for an {@link Annotation}
     * @return the {@link Annotation} that was found or null.
     */
    private <T extends Annotation> T findMethodAnnotation(Class<T> annotationClass,
                                                          MethodParameter parameter) {
        T annotation = parameter.getParameterAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        Annotation[] annotationsToSearch = parameter.getParameterAnnotations();
        for (Annotation toSearch : annotationsToSearch) {
            annotation = AnnotationUtils.findAnnotation(toSearch.annotationType(),
                    annotationClass);
            if (annotation != null) {
                return annotation;
            }
        }
        return null;
    }
}
